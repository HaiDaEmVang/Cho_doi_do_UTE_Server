package org.hcv.chodoido_ute_service.mapper;

import org.hcv.chodoido_ute_service.dto.request.ProductRequest;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    void updateProduct(@MappingTarget Product product, ProductRequest productRequest);
}
