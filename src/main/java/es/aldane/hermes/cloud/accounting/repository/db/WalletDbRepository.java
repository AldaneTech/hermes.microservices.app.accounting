package es.aldane.hermes.cloud.accounting.repository.db;

import es.aldane.hermes.cloud.accounting.repository.db.entity.WalletDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletDbRepository extends JpaRepository<WalletDb, Long> {
    List<WalletDb> findByUserId(Long userId);
}
