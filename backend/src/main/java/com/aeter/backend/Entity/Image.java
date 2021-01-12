package com.aeter.backend.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "img")
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
    @Column(name = "filepath")
    private String filePath;

    @OneToMany(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageTag> imageTags;

}
