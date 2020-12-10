package com.aeter.training.DTO;

import com.aeter.training.Entity.Image;
import com.aeter.training.Entity.ImageTag;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {


    @Setter
    String filePath;
    @Setter
    List<ImageTag> tags;



}
