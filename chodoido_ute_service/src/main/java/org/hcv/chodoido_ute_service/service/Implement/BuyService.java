package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.applicationInitConfig.ApplicationInitConfig;
import org.hcv.chodoido_ute_service.dto.request.BuyRequest;
import org.hcv.chodoido_ute_service.dto.response.BuyDTO;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.entity.*;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.BuyMapper;
import org.hcv.chodoido_ute_service.mapper.ProductMapper;
import org.hcv.chodoido_ute_service.repository.BuyRepository;
import org.hcv.chodoido_ute_service.repository.MissionRepository;
import org.hcv.chodoido_ute_service.service.Interface.IBuyService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BuyService implements IBuyService {
    BuyRepository buyRepository;
    UserService userService;
    ProductService productService;
    ProductMapper productMapper;
    BuyMapper buyMapper;
    BuyStatus DEFAUT_STATUS = BuyStatus.CHO_XAC_NHAN;
    MissionRepository missionRepository;

    @Override
    public BuyDTO findBuyId(Long idBuy) {
        return buyMapper.buyToBuyDTO(buyRepository.findById(idBuy).orElseThrow(()-> new NotFoundException("Not found by id: " + idBuy)));
    }
    @Override
    public Buy findBuyBuyId(Long idBuy) {
        return buyRepository.findById(idBuy).orElseThrow(()-> new NotFoundException("Not found by id: " + idBuy));
    }

    @Override
    public List<ProductDTO> findByUser(Long idUser) {
        User user = userService.findByID(idUser);
        return buyRepository.findByUser(user)
                .stream().map(productMapper::toProductDTO).toList();
    }

    @Override
    public int countProductByUser(Long idUser) {
        User user = userService.findByID(idUser);
        return buyRepository.countProductByUser(user);
    }

    @Override
    public List<ProductDTO> findByStatusAndUser(BuyStatus stauts, Long idUser) {
        User user = userService.findByID(idUser);
        return buyRepository.findByUserAndStatus(user, stauts)
                .stream().map(productMapper::toProductDTO).toList();
    }

    @Override
    public BuyDTO addBuy(BuyRequest buyRequest) {
        buyRequest.setId(0L);
        User u = userService.findByID(buyRequest.getIdUser());
        Product p = productService.findById(buyRequest.getIdProduct());
        if(p.getUser() == u)
            throw new NoActionException("You can't add your product");

        if(p.getCount() - buyRequest.getCount() < 0)
            throw new NoActionException("Not enough products to add buy");
        p.setCount(- buyRequest.getCount());
        if(p.getCount() == 0)
            p.setPostProductStatus(PostProductStatus.DA_AN);
        p = productService.save(p);

        Buy newBuy = null;
        try{
            Buy buy = Buy.builder()
                    .id(0L).price(p.getPrice()).count(buyRequest.getCount())
                    .product(p).user(u).status(DEFAUT_STATUS).timeBuy(LocalDate.now()).build();
            newBuy = buyRepository.save(buy);
        }catch (Exception ex){
            throw new NoActionException("Server Err not add BuyService");
        }
        return buyMapper.buyToBuyDTO(newBuy);
    }

    @Override
    public BuyDTO updateStatus(Long idBuy, BuyStatus status) {
        Buy buy = findBuyBuyId(idBuy);
        buy.setStatus(status);
        if(status == BuyStatus.THANH_CONG){
            if(ApplicationInitConfig.MISSION_BANHANGTHANHCONG != null)
                buy.getProduct().getUser().setPoint(ApplicationInitConfig.MISSION_BANHANGTHANHCONG.getPoint());
            else
                log.error("User sell product can not set point/ mission default not init /");
            if(ApplicationInitConfig.MISSION_MUAHANGTHANHCONG != null)
                buy.getUser().setPoint(ApplicationInitConfig.MISSION_MUAHANGTHANHCONG.getPoint());
            else
                log.error("User can not set point/ mission default not init /");
        }
        if(status == BuyStatus.THAT_BAI)
            buy.getProduct().setCount(buy.getCount());
        buy.getUser().setProductSuccess();
        buy.getUser().setProductLost();
        Buy newBuy = buyRepository.save(buy);
        return buyMapper.buyToBuyDTO(newBuy);
    }

    @Override
    public void deleteBuy(Long idBuy) {
        buyRepository.deleteById(idBuy);
    }
}
