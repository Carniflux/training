package com.aeter.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @NonNull
    @Column(name = "file_name")
    private String fileName;

    @OneToMany(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageTag> imageTags;
}
