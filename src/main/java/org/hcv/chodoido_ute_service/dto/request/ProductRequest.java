package org.hcv.chodoido_ute_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.entity.PostProductStatus;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Long id;
    String title;
    String description;
    Double price;
    boolean isNew;
    Long count;
    Long idCategory;
    String email;
}
