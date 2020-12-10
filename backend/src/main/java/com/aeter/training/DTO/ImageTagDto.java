package com.aeter.training.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageTagDto {

    private String imageTag;
    private Long imageId;
}
