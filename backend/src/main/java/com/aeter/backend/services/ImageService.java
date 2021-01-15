package com.aeter.backend.services;


import com.aeter.backend.DTO.ImageDto;
import com.aeter.backend.detect.DetectLabel;
import com.aeter.backend.entity.Image;
import com.aeter.backend.entity.ImageTag;
import com.aeter.backend.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ImageService {

    final private ImageRepository imageRepository;

    public List<ImageDto> getAll() {
        return ((List<Image>) imageRepository.findAll())
            .stream()
            .map(this::convertToImageDto)
            .collect(Collectors.toList());
}

    private ImageDto convertToImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setFileName(image.getFileName());
        List<String> tag = new ArrayList<>();
        for(ImageTag t : image.getImageTags()){
            tag.add(t.getImageTag());
        }
        imageDto.setImageTagsDto(tag);
        return imageDto;
    }

    public void addTag(final Image image, String path) throws IOException {

        final Image toCreate = Image.builder()
                .fileName(image.getFileName())
                .build();

        List<ImageTag> tags = new ArrayList<>();
        for (String s : DetectLabel.detectLabel(path)) {
            ImageTag imageTag = new ImageTag(s, toCreate);
            imageTag.setImage(toCreate);
            tags.add(imageTag);
        }
        toCreate.setImageTags(tags);
        imageRepository.save(toCreate);
    }
}
