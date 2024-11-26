package org.hcv.chodoido_ute_service.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.entity.CommentImg;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.entity.Role;
import org.hcv.chodoido_ute_service.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {
    Long id;
    ProductDTO product;
    UserDTO user;
    LocalDate timePost;
    String content;
    int rate;
    List<CommentImg> images = new ArrayList<>();

}
