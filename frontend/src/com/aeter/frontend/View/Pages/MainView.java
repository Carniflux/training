package com.aeter.frontend.View.Pages;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Route("upload")
public class MainView extends VerticalLayout {
    @Autowired
    private final RestTemplate restTemplate;

    private final String IMG_URI_UPLOAD = "http://localhost:8081/images/upload/";


    public MainView(){
        this.restTemplate = new RestTemplate();

//        MemoryBuffer memoryBuffer = new MemoryBuffer();
//        Upload upload = new Upload(memoryBuffer);
//
//        upload.setMaxFileSize(2097152);
        //Div output = new Div();

        //upload.addStartedListener(e -> System.out.println("Download has started"));

//        upload.addSucceededListener(event -> { System.out.println("Download has finished");
//            try {
//                HttpEntity<MultiValueMap<String, Object>> request = getRequest(memoryBuffer);
//
//            } catch (IOException | HttpClientErrorException e) {
//                e.printStackTrace();
//                System.out.println("fail");
//            }
//        });
        add(new RouterLink("ListImages", ListImage.class));
        add(uploadImage());
        }

    private Upload uploadImage(){
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload upload = new Upload(memoryBuffer);


        upload.addFinishedListener(event -> { System.out.println("Download has finished");
            try {
                HttpEntity<MultiValueMap<String, Object>> request = getRequest(memoryBuffer);
                responseUploading(request);
            } catch (IOException | HttpClientErrorException e) {
                e.printStackTrace();
                System.out.println("fail");
            }
        });

        return upload;
    }
    private byte[] getBytes(MemoryBuffer fileBuffer) throws IOException {
        byte[] bytes;
        bytes = new byte[fileBuffer.getInputStream().available()];
        int read = fileBuffer.getInputStream().read(bytes);
        return bytes;
    }

    private HttpEntity<byte[]> getHttpEntity(MemoryBuffer fileBuffer) throws IOException {
        byte[] bytes = getBytes(fileBuffer);
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(fileBuffer.getFileName())
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        fileMap.add("content-type", "image/*");
        return new HttpEntity<>(bytes, fileMap);
    }

    private HttpEntity<MultiValueMap<String, Object>> getRequest(MemoryBuffer fileBuffer) throws IOException {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<byte[]> fileEntity = getHttpEntity(fileBuffer);
        body.add("file", fileEntity);
        return new HttpEntity<>(body, headers);
    }

    private ResponseEntity<Void> responseUploading(HttpEntity<MultiValueMap<String, Object>> request) throws IOException {
        return restTemplate.postForEntity(IMG_URI_UPLOAD,
                request,
                void.class);

    }




}
