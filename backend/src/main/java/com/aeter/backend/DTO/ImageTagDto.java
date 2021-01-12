package com.aeter.backend.DTO;


import lombok.*;


@NoArgsConstructor
public class ImageTagDto {

    @Setter
    private Long tagId;
    @Setter
    private String imageTag;
    @Setter
    private Long imageId;

public ImageTagDto (Long tagId, String imageTag, Long imageId) {
    this.tagId = tagId;
    this.imageTag = imageTag;
    this.imageId = imageId;
}



}
