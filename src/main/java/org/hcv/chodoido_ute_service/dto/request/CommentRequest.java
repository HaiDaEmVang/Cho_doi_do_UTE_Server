package org.hcv.chodoido_ute_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.CommentImg;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {
        Long id;
        Long idProduct;
        Long idUser;
        String content;
        int rate;
}
