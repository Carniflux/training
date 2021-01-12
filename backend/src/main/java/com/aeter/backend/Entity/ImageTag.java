package com.aeter.backend.Entity;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tag")
public class ImageTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "imgtag")
    private String imageTag;



    @Setter
    @ManyToOne
    @JoinColumn(name = "img_id")
    private Image image;


    public ImageTag( String imageTag, Image image) {
        this.imageTag = imageTag;
        this.image = image;
    }
}
