package com.aeter.backend.Services;


import com.aeter.backend.DTO.ImageDto;
import com.aeter.backend.Entity.Image;
import com.aeter.backend.Entity.ImageTag;
import com.aeter.backend.Repository.ImageRepository;
import com.aeter.backend.Repository.TagRepository;
import com.aeter.backend.detect.DetectLabel;
import com.google.api.client.util.IOUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ImageService {


    private ImageRepository imageRepository;
    private TagRepository tagRepository;


    public List<ImageDto> getAll() {
        return ((List<Image>) imageRepository.findAll())
                .stream()
                .map(this::convertToImageDto)
                .collect(Collectors.toList());
    }


    public List<ImageDto> getByTag(String tag) {
        return ((List<Image>) imageRepository.findByImageTags(tag))
                .stream()
                .map(this::convertToImageDto)
                .collect(Collectors.toList());

    }

    private ImageDto convertToImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
       // imageDto.setFilePath(image.getFilePath());
        List<String> tag = new ArrayList<>();
        for(ImageTag t : image.getImageTags()){
            tag.add(t.getImageTag());
        }
        imageDto.setImageTagsDto(tag);
        return imageDto;
    }



    @Transactional
    public Image addTag(final Image image, String path) throws IOException {



        final Image toCreate = Image.builder()
                //.id(image.getId())
                .filePath(image.getFilePath())
                //.imageTags(dto.getImageTagsDto())
                .build();



        List<ImageTag> tags = new ArrayList<>();
        for (String s : DetectLabel.detectLabel(path)) {
            ImageTag imageTag = new ImageTag(s, toCreate);
            //ImageTag imageTag = new ImageTag();
            imageTag.setImage(toCreate);
            //imageTag.setImage(imageTagDto.getImageDto());
            tags.add(imageTag);

        }

        toCreate.setImageTags(tags);

        return imageRepository.save(toCreate);


    }

    public Optional<Image> findById(final Long id) {
        return imageRepository.findById(id);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
