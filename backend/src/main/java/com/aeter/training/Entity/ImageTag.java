package com.aeter.training.Entity;


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
    @NonNull
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "imgtag")
    private String imageTag;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "img_id", nullable = false)
    private Image image;



}
