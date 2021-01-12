package com.aeter.backend.Repository;

import com.aeter.backend.Entity.Image;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {


    @Query("from Image i " +
                "where " +
            "concat(i.imageTags, ' ') like concat('%', :tag, '%')")
    List<Image> findByImageTags(@Param("tag") String tag);
}
