package com.example.demo.repository;

import com.example.demo.model.Submit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitRepository extends JpaRepository<Submit, Long> {

    @Query(name = "SELECT s FROM submit s WHERE user_id = :id", nativeQuery = true)
    Page<Submit> findSubmitsByUserId(Long id, Pageable pageable);

    @Query("select count(s.id) from Submit s where s.user.id=?1")
    Integer countById(Long id);
}
