package com.aeter.backend.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "image_tag")
public class ImageTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_tag")
    private String imageTag;

    @Setter
    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public ImageTag( String imageTag, Image image) {
        this.imageTag = imageTag;
        this.image = image;
    }
}
