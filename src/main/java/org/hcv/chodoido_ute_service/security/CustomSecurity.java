package org.hcv.chodoido_ute_service.security;

import org.hcv.chodoido_ute_service.entity.*;
import org.hcv.chodoido_ute_service.service.Implement.BuyService;
import org.hcv.chodoido_ute_service.service.Implement.CommentService;
import org.hcv.chodoido_ute_service.service.Implement.ProductService;
import org.hcv.chodoido_ute_service.service.Implement.UserService;
import org.springframework.stereotype.Component;

@Component("customSecurity")
public class CustomSecurity {
    private final UserService userService;
    private final ProductService productService;
    private final CommentService commentService;
    private final BuyService buyService;

    public CustomSecurity(UserService userService, ProductService productService, CommentService commentService, BuyService buyService) {
        this.userService = userService;
        this.productService = productService;
        this.commentService = commentService;
        this.buyService = buyService;
    }

    public boolean isOwner(Long userId, String username) {
        User user = userService.findByID(userId);
        return user != null && user.getEmail().equals(username);
    }

    public boolean isOwnerProduct(Long idProduct, String username) {
        Product product = productService.findById(idProduct);
        return product != null && product.getUser().getEmail().equals(username);
    }

    public boolean isOwnerComment(Long idComment, String username) {
        Comment comment = commentService.findCommentById(idComment);
        return comment != null && comment.getUser().getEmail().equals(username);
    }

    public boolean isSuccessUpdateBuy(Long idBuy, String userUpdate, BuyStatus status){
        Buy buy = buyService.findBuyBuyId(idBuy);
        if(status == BuyStatus.DANG_GIAO_DICH && userUpdate.equals(buy.getProduct().getUser().getEmail()))
            return true;
        if(status == BuyStatus.THANH_CONG && (userUpdate.equals(buy.getUser().getEmail()) || userUpdate.equals(buy.getProduct().getUser().getEmail())))
            return true;
        return status == BuyStatus.DA_HUY && (userUpdate.equals(buy.getUser().getEmail()) || userUpdate.equals(buy.getProduct().getUser().getEmail()));
    }

    public boolean isOwnerBuy(Long idBuy, String userUpdate){
        Buy buy = buyService.findBuyBuyId(idBuy);
        return buy != null && buy.getUser().getEmail().equals(userUpdate);
    }
}

