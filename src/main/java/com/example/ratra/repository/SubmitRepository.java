package com.example.ratra.repository;

import com.example.ratra.model.Submit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitRepository extends JpaRepository<Submit, Long> {

    @Query(name = "SELECT s FROM submits s WHERE user_id = :id", nativeQuery = true)
    Page<Submit> findSubmitsByUserId(Long id, Pageable pageable);

    @Query("select count(s.id) from Submit s where s.user.id=?1")
    Integer countById(Long id);
}
