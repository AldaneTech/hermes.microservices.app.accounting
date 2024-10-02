package es.aldane.hermes.cloud.accounting.repository.db;

import es.aldane.hermes.cloud.accounting.repository.db.entity.AccountEntryTypeDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntryTypeDbRepository extends JpaRepository<AccountEntryTypeDb, Long> {
}
