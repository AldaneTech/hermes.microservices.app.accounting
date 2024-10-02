package es.aldane.hermes.cloud.accounting.mapper;

import es.aldane.hermes.cloud.accounting.repository.db.entity.CategoryDb;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper
public interface CategoryMapper {
    @Mapping(source = "lastModification", target = "lastModification", qualifiedByName = "localDateTimeToOffsetDateTime")
    Category categoryDbToCategory(CategoryDb categoryDb);

    List<Category> categoryDbListToCategoryList(List<CategoryDb> categoryDb);

    @Mapping(source = "lastModification", target = "lastModification", qualifiedByName = "offsetDateTimeToLocalDateTime")
    CategoryDb categoryToCategoryDb(Category category);

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }
}
