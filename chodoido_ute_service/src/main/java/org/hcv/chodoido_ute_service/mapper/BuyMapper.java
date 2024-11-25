package org.hcv.chodoido_ute_service.mapper;

import org.hcv.chodoido_ute_service.dto.request.BuyRequest;
import org.hcv.chodoido_ute_service.dto.response.BuyDTO;
import org.hcv.chodoido_ute_service.entity.Buy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface BuyMapper {
    BuyDTO buyToBuyDTO(Buy buy) ;
}
