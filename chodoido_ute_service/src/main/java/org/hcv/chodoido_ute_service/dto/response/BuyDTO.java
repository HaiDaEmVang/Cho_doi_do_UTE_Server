package org.hcv.chodoido_ute_service.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hcv.chodoido_ute_service.entity.BuyStatus;
import org.hcv.chodoido_ute_service.entity.Category;
import org.hcv.chodoido_ute_service.entity.Product;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyDTO {
    Long id;
    UserDTO user;
    ProductDTO product;
    LocalDate timeBuy;
    String status;
    Long count;
    Double price;

}
