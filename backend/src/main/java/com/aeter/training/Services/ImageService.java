package com.aeter.training.Services;

import com.aeter.training.DTO.ImageDto;
import com.aeter.training.DTO.ImageTagDto;
import com.aeter.training.Entity.Image;
import com.aeter.training.Entity.ImageTag;
import com.aeter.training.Repository.ImageRepository;
import com.aeter.training.Repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ImageService {


    private ImageRepository imageRepository;
    private TagRepository tagRepository;


    public Optional<Image> findById(Long id) {
        return  imageRepository.findById(id);

    }

    @Transactional
    public Image create(final ImageDto dto, String fileName) {

        final Image toCreate = Image.builder()
                .filePath(dto.getFilePath())
                .imageTags(dto.getTags())
                .build();

        return imageRepository.save(toCreate);
    }

    public ImageTag createTag(final ImageTagDto tagDto){
        final ImageTag toCreateTag = ImageTag.builder()
                .imageTag(tagDto.getImageTag())
                .build();
        return tagRepository.save(toCreateTag);
    }


    public Image createTest(final  ImageDto dto) {

        final Image toCreateTest = Image.builder()
                .filePath(dto.getFilePath())
                .build();
        return imageRepository.save(toCreateTest);
    }



    public void delete(Long id) {
        imageRepository.deleteById(id);
    }
}
