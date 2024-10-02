package es.aldane.hermes.cloud.accounting.mapper;

import es.aldane.hermes.cloud.accounting.repository.db.entity.AccountEntryDb;
import es.aldane.hermes.cloud.accounting_api_server_java.model.AccountEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {UserMapper.class, BrandMapper.class, StoreMapper.class, CategoryMapper.class, WalletMapper.class, AccountEntryTypeMapper.class})
public interface AccountEntryMapper {
    @Mapping(source = "accountEntryType", target = "typeOperation")
    AccountEntry accountEntryDbToAccountEntry(AccountEntryDb accountEntry);

    List<AccountEntry> accountEntryDbListToAccountEntryList(List<AccountEntryDb> accountEntry);

    @Mapping(source = "typeOperation", target = "accountEntryType")
    AccountEntryDb accountEntryToAccountEntryDb(AccountEntry accountEntry);
}
