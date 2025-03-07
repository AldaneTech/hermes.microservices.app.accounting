package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting_api_server_java.model.AccountEntry;

import java.util.List;

public interface AccountEntryService {

    List<AccountEntry> getAccountEntries(List<String> accountEntrysIds);

    AccountEntry getAccountEntryById(Long accountEntryId);

    AccountEntry createAccountEntry(AccountEntry accountEntry);

    boolean deleteAccountEntry(Long id);

    AccountEntry updateAccountEntry(AccountEntry accountEntry);

    List<AccountEntry> getAccountEntryByWalletId(Long walletId);
}
