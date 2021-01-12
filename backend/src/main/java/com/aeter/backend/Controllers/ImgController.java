package com.aeter.backend.Controllers;


import com.aeter.backend.DTO.ImageDto;
import com.aeter.backend.Entity.Image;
import com.aeter.backend.Services.ImageService;
import com.google.api.client.util.IOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


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
        System.out.println("1");


        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setFilePath(uploadFolderPath + file.getOriginalFilename());

        final Image fileCreated = imageService.addTag(image, image.getFilePath());

    }



//    public ResponseEntity<byte[]> download(@PathVariable("id") final Long id) {
//        final Optional<Image> fileOpt = imageService.findById(id);
//        if (fileOpt.isPresent()) {
//            final Image image = fileOpt.get();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "\"")
//                    .body(image.getId());
//        }
//
//        return ResponseEntity.notFound().build();
//    }
    @GetMapping("/filtered")
    @ApiOperation(value = "Filtered search")
    @ApiResponses({
            @ApiResponse(code = 200, message = "File found."),
            @ApiResponse(code = 404, message = "File not found.")
    })
    public List<ImageDto> findByTag(@RequestParam(name = "tag") String tag) {
        final List<ImageDto> fileOpt = imageService.getByTag(tag);
//        if (fileOpt.isPresent()) {
//            return ResponseEntity.ok(fileOpt.get());
//        }

        return fileOpt;
    }

    @GetMapping("/map")
    @ApiOperation(value = "Searching all images")
    public List<ImageDto> read() {
        final List<ImageDto> image = imageService.getAll();
        return  image;
    }


//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete")
//    public ResponseEntity delete(@PathVariable("id") final Long id) {}
//
}
