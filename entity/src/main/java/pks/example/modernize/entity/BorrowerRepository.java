package pks.example.modernize.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BorrowerRepository extends JpaRepository<BorrowerEntity,BorrowerEntityPk> {

    List<BorrowerEntity> findByName(String name);
    List<BorrowerEntity> findByOrganization(String organization);
    List<BorrowerEntity> findByPhone(String phone);

    @Query("SELECT bwr FROM BorrowerEntity bwr WHERE bwr.pk.code = :str")
    List<BorrowerEntity> listByCode(String str);

}
