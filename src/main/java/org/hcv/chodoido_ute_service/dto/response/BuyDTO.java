package org.hcv.chodoido_ute_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyDTO {
    Long id;
    UserDTO user;
    ProductDTO product;
    LocalDateTime timeBuy;
    String status;
    Long count;
    Double price;
    Boolean isComment;

}
