package es.aldane.hermes.cloud.accounting.mapper;

import es.aldane.hermes.cloud.accounting.repository.db.entity.AccountEntryTypeDb;
import es.aldane.hermes.cloud.accounting_api_server_java.model.AccountEntryType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper
public interface AccountEntryTypeMapper {
    @Mapping(source = "lastModification", target = "lastModification", qualifiedByName = "localDateTimeToOffsetDateTime")
    AccountEntryType accountEntryTypeDbToAccountEntryType(AccountEntryTypeDb accountEntryType);

    List<AccountEntryType> accountEntryTypeDbListToAccountEntryTypeList(List<AccountEntryTypeDb> accountEntryType);

    @Mapping(source = "lastModification", target = "lastModification", qualifiedByName = "offsetDateTimeToLocalDateTime")
    AccountEntryTypeDb accountEntryTypeToAccountEntryTypeDb(AccountEntryType brand);

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }
}
