package org.hcv.chodoido_ute_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Product")
    Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_User")
    User user;

    LocalDateTime timePost;
    String content;
    int rate;

    @JsonManagedReference
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    List<CommentImg> images = new ArrayList<>();

    public List<CommentImg> getImages() {
        if(images == null)
            this.images = new ArrayList<>();
        return images;
    }

}
