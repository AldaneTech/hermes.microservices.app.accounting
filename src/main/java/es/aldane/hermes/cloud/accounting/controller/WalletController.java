package es.aldane.hermes.cloud.accounting.controller;

import es.aldane.hermes.cloud.accounting.service.WalletService;
import es.aldane.hermes.cloud.accounting_api_server_java.api.WalletApi;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WalletController implements WalletApi {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public ResponseEntity<Wallet> createWallet(@Valid Wallet wallet) {
        var newWallet = walletService.createWallet(wallet);
        return newWallet != null ? ResponseEntity.ok(newWallet) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> deleteWallet(Long walletId) {
        var wallet = walletService.deleteWallet(walletId);
        return wallet ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Wallet> getWalletById(Long walletId) {
        var wallet = walletService.getWalletById(walletId);
        return wallet != null ? ResponseEntity.ok(wallet) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Wallet>> getWalletsByUserId(Long idUser) {
        var wallets = walletService.getWalletsByUserId(idUser);
        return !wallets.isEmpty() ? ResponseEntity.ok(wallets) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Wallet>> getWallets() {
        var states = walletService.getWallets(new ArrayList<>());
        return ResponseEntity.ok(states);
    }

    @Override
    public ResponseEntity<Wallet> updateWallet(@Valid Wallet wallet) {
        return walletService.updateWallet(wallet) != null ? ResponseEntity.ok(wallet) : ResponseEntity.badRequest().build();
    }
}
