package com.aeter.frontend.view;

import com.aeter.frontend.DTO.ImageDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Route("ListImages")
public class ListImage  extends VerticalLayout {
    
    @Autowired
    private final RestTemplate restTemplate;
    private final String imageUri = "http://localhost:8081/images/map";
    private final Grid<ImageDto> grid;

    public ListImage(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.grid = new Grid<>(ImageDto.class);
        add(new RouterLink("MainPage", MainView.class));
        add(grid);
        grid.removeColumnByKey("imageTagsDto");
        grid.removeColumnByKey("id");
        grid.addColumn(imageDto -> imageDto.getImageTagsDto() ==null?null:imageDto.getImageTagsDto()
                .stream()
                .collect(Collectors.joining(", " )));
        listImage();
    }

    private void listImage() {
        ResponseEntity<List<ImageDto>> rateResponse =
                restTemplate.exchange(imageUri,
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
        List<ImageDto> dto= rateResponse.getBody();
        grid.setItems(dto);
    }
}
