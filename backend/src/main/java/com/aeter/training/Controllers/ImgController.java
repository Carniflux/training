package com.aeter.training.Controllers;


import com.aeter.training.DTO.ImageDto;
import com.aeter.training.DTO.ImageTagDto;
import com.aeter.training.Entity.Image;
import com.aeter.training.Entity.ImageTag;
import com.aeter.training.Services.ImageService;
import com.aeter.training.detect.DetectLabel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/images")
@Api(value = "ImgControllerAPI", tags = "img")
@AllArgsConstructor
public class ImgController {


    private final ImageService imageService;
    private final String uploadFolderPath = "/home/slava/";



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Saves Image instance.", tags = "img")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK", response = Image.class)})
    public ResponseEntity create(@RequestPart(name = "file") MultipartFile file) throws IOException {
        ImageDto model = new ImageDto();

        List<String> tags;
        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.setFilePath(uploadFolderPath + file.getOriginalFilename());
        tags =
        model.setTags(DetectLabel.detectLabel(model.getFilePath()));
        final Image fileCreated = imageService.create(model, file.getOriginalFilename());
        //final ImageTag tagCreated = imageService.createTag();
        return ResponseEntity.status(HttpStatus.CREATED).body(fileCreated);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Reads a File instance.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "File found."),
            @ApiResponse(code = 404, message = "File not found.")
    })
    public ResponseEntity read(@PathVariable("id") final Long id) {
        final Optional<Image> fileOpt = imageService.findById(id);
        if (fileOpt.isPresent()) {
            return ResponseEntity.ok(fileOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

//    @PostMapping(consumes = {APPLICATION_OCTET_STREAM_VALUE})
//    @ApiOperation(value = "Testing")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Image.class)})
//    public ResponseEntity createTest(@RequestPart(name = "model") @Valid final ImageDto model){
//        final Image fileCreated = imageService.createTest(model);
//        return ResponseEntity.status(HttpStatus.CREATED).body(fileCreated);
//    }
//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete")
//    public ResponseEntity delete(@PathVariable("id") final Long id) {}
//
}
