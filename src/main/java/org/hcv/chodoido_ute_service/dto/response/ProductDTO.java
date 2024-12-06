package org.hcv.chodoido_ute_service.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

    Long id;
    String title;
    String description;
    Double price;
    LocalDateTime timePost;
    boolean isNew;
    int count;
    UserDTO user;
    Category category;

    PostProductStatus postProductStatus;

    List<ProductImg> productImgs;

    List<ProductVideo> productVideos;
}
