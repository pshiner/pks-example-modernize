package pks.example.modernize.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<BorrowerEntity,BorrowerEntityPk> {

}
