package com.aeter.frontend.View.Pages;

import com.aeter.frontend.DTO.ImageDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Route("ListImages")
public class ListImage  extends VerticalLayout {


    private final String imageUri = "http://localhost:8081/images/map";
    private final Grid<ImageDto> grid;
    private final Div div;
    @Autowired
    private final RestTemplate restTemplate;

    public ListImage(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.grid = new Grid<>(ImageDto.class);
        this.div = new Div();
        add(new RouterLink("MainPage", MainView.class));
        add(grid);
        grid.removeColumnByKey("imageTagsDto");
        listImage();

        grid.addColumn(imageDto -> imageDto.getImageTagsDto()==null?null:imageDto.getImageTagsDto().stream()
        .collect(Collectors.joining(", " )));

    }

    private void listImage() {

        ResponseEntity<List<ImageDto>> rateResponse =
                restTemplate.exchange(imageUri,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ImageDto>>() {
                        });
        List<ImageDto> dto= rateResponse.getBody();


        grid.setItems(dto);
    }
}
