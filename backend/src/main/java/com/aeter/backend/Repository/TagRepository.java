package com.aeter.backend.Repository;

import com.aeter.backend.Entity.ImageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<ImageTag, Long> {
    ImageTag findByImageTag(ImageTag String);
}
