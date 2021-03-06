package com.aeter.backend.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    Long id;

    String fileName;

    List<String> imageTagsDto;
}
