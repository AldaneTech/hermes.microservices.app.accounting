package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting_api_server_java.model.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> getWallets(List<String> walletsIds);

    Wallet getWalletById(Long walletId);

    Wallet createWallet(Wallet wallet);

    boolean deleteWallet(Long id);

    Wallet updateWallet(Wallet wallet);

    List<Wallet> getWalletsByUserId(Long idUser);
}
