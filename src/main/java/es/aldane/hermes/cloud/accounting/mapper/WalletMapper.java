package es.aldane.hermes.cloud.accounting.mapper;

import es.aldane.hermes.cloud.accounting.repository.db.entity.WalletDb;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(uses = {UserMapper.class})
public interface WalletMapper {
    @Mappings({
            @Mapping(source = "lastModification", target = "lastModification", qualifiedByName = "localDateTimeToOffsetDateTime"),
            @Mapping(source = "user", target = "user")  // MapStruct usará UserMapper automáticamente
    })    Wallet walletDbToWallet(WalletDb walletDb);
    List<Wallet> walletDbListToWalletList(List<WalletDb> walletDb);
    @Mappings({
            @Mapping(source = "lastModification", target = "lastModification", qualifiedByName = "offsetDateTimeToLocalDateTime"),
            @Mapping(source = "user", target = "user")  // MapStruct usará UserMapper automáticamente
    })    WalletDb walletToWalletDb(Wallet wallet);

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }
}
