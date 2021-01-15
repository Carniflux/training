package com.aeter.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Route("upload")
public class MainView extends VerticalLayout {
    @Autowired
    private final RestTemplate restTemplate;
    private final String imgUri = "http://localhost:8081/images/upload/";

    public MainView() {
        this.restTemplate = new RestTemplate();
        add(new RouterLink("ListImages", ListImage.class));
        add(uploadImage());
        }

    private Upload uploadImage(){
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload upload = new Upload(memoryBuffer);
        upload.addSucceededListener(event -> { System.out.println("Download has finished");
            try {
                HttpEntity<MultiValueMap<String, Object>> request = getRequest(memoryBuffer);
                responseUploading(request);
            } catch (IOException e) {
                throw new RuntimeException("fail", e);

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

    private void responseUploading(HttpEntity<MultiValueMap<String, Object>> request) throws IOException {
         restTemplate.postForEntity(imgUri,
                request,
                byte[].class);
    }
}
