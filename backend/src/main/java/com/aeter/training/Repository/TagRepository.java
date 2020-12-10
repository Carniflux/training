package com.aeter.training.Repository;

import com.aeter.training.Entity.Image;
import com.aeter.training.Entity.ImageTag;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<ImageTag, Long> {
    ImageTag findByimageTag(ImageTag String);
}
