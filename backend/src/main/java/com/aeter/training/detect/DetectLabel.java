package com.aeter.training.detect;

import com.aeter.training.DTO.ImageDto;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DetectLabel {

//    public static void detectLabels() throws IOException {
//        //ImageDto dto = ImageDto.builder().build();
//        String fileName = "qqq.jpg";
//        String filePath = "/home/slava/Project/training/backend/src/main/resources/" + fileName;
//        detectLabel(filePath);
//    }

    public static List<String> detectLabel(String filePath) throws IOException {
        List<String> tags = new ArrayList<>(6);
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder()
                        .addFeatures(feat)
                        .setImage(img)
                        .build();
        requests.add(request);


        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {


                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {

                    tags.add(annotation.getDescription());


                }


            }
        }

        return tags;
    }
}

