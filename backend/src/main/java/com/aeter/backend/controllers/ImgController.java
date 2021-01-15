package com.aeter.backend.controllers;

import com.aeter.backend.DTO.ImageDto;
import com.aeter.backend.entity.Image;
import com.aeter.backend.services.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/images")
@Api(value = "ImgControllerAPI", tags = "img")
@AllArgsConstructor
public class ImgController {

    private final ImageService imageService;
    private final String uploadFolderPath = "/home/slava/";

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Saves Image instance.", tags = "img")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK", response = Image.class)})
    public void create(@RequestPart(name = "file") MultipartFile file) throws IOException {
        Image image = new Image();
        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException("fail", e);
        }
        image.setFileName(file.getOriginalFilename());
        imageService.addTag(image, uploadFolderPath + image.getFileName());
    }

    @GetMapping("/map")
    @ApiOperation(value = "Searching all images")
    public List<ImageDto> read() {
        return imageService.getAll();

    }
}
