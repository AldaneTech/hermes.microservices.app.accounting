package es.aldane.hermes.cloud.accounting.repository.db;

import es.aldane.hermes.cloud.accounting.repository.db.entity.AccountEntryDb;
import es.aldane.hermes.cloud.accounting.repository.db.entity.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountEntryDbRepository extends JpaRepository<AccountEntryDb, Long> {
    List<AccountEntryDb> findByWallet_IdOrderByDateDesc(Long walletId);

}
