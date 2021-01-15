package com.aeter.frontend.DTO;

import lombok.*;

import java.util.List;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

        Long id;

        String fileName;

        List<String> imageTagsDto;
}
