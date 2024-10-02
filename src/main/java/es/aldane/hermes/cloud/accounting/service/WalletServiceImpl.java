package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting.mapper.WalletMapper;
import es.aldane.hermes.cloud.accounting.repository.db.WalletDbRepository;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Wallet;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletDbRepository walletDbRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImpl(WalletDbRepository walletDbRepository, WalletMapper walletMapper) {
        this.walletDbRepository = walletDbRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public List<Wallet> getWallets(List<String> statesIds) {
        try {
            var walletsList = walletDbRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            return walletMapper.walletDbListToWalletList(walletsList);
        } catch (Exception e) {
            System.out.println("Error obtaining wallets");
            return new ArrayList<>();
        }
    }

    @Override
    public Wallet getWalletById(Long walletId) {
        try {
            var wallet = walletDbRepository.findById(walletId).orElse(null);
            return walletMapper.walletDbToWallet(wallet);
        } catch (Exception e) {
            System.out.println("Error obtaining wallet with id: " + walletId);
            return null;
        }
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        try {
            wallet.setLastModification(OffsetDateTime.now());
            wallet.setBudget(0.0F);
            var walletSaved = walletDbRepository.save(walletMapper.walletToWalletDb(wallet));
            return walletMapper.walletDbToWallet(walletSaved);
        } catch (Exception e) {
            System.out.println("Error creating wallet");
            return null;
        }
    }

    @Override
    public boolean deleteWallet(Long id) {
        try {
            walletDbRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting wallet with id: " + id);
            return false;
        }
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        try {
            wallet.setLastModification(OffsetDateTime.now());
            var walletDb = walletDbRepository.findById(wallet.getId()).orElse(null);
            walletDb.setName(wallet.getName());
            walletDb.setComment(wallet.getComment());
            return walletMapper.walletDbToWallet(walletDbRepository.save(walletDb));
        } catch (Exception e) {
            System.out.println("Error updating wallet with id: " + wallet.getId());
            return null;
        }
    }

    @Override
    public List<Wallet> getWalletsByUserId(Long idUser) {
        try {
            return walletMapper.walletDbListToWalletList(walletDbRepository.findByUserId(idUser));
        } catch (Exception e) {
            System.out.println("Error obtaining wallets for user with id: " + idUser);
            return null;
        }
    }
}
