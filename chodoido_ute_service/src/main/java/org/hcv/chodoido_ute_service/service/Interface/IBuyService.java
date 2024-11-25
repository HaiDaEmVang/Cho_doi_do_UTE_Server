package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.dto.request.BuyRequest;
import org.hcv.chodoido_ute_service.dto.response.BuyDTO;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.entity.Buy;
import org.hcv.chodoido_ute_service.entity.BuyStatus;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBuyService {

    BuyDTO findBuyId(Long idBuy);
    Buy findBuyBuyId(Long idBuy);

    List<ProductDTO> findByUser(Long idUser);

    int countProductByUser(Long idUser);

    List<ProductDTO> findByStatusAndUser(BuyStatus stauts, Long idUser);

    BuyDTO addBuy(BuyRequest buyRequest);

    BuyDTO updateStatus(Long idBuy, BuyStatus stauts);

    void deleteBuy(Long idBuy);

}
