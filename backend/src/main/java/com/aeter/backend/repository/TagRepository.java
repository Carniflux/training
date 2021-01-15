package com.aeter.backend.repository;

import com.aeter.backend.entity.ImageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<ImageTag, Long> {
}
