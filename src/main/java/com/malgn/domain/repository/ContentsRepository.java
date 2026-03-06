package com.malgn.domain.repository;

import com.malgn.domain.entity.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {

    Page<Contents> findAll(Pageable pageable);

}
