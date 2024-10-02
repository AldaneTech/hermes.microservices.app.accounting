package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting_api_server_java.model.AccountEntryType;

import java.util.List;

public interface AccountEntryTypeService {
    List<AccountEntryType> getAccountEntryTypes(List<String> accountEntryTypesIds);

    AccountEntryType getAccountEntryTypeById(Long accountEntryTypeId);

    AccountEntryType createAccountEntryType(AccountEntryType accountEntryType);

    boolean deleteAccountEntryType(Long id);

    AccountEntryType updateAccountEntryType(AccountEntryType accountEntryType);
}
