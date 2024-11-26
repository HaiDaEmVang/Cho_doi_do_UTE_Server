package org.hcv.chodoido_ute_service.security;

import org.hcv.chodoido_ute_service.entity.Comment;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.entity.User;
import org.hcv.chodoido_ute_service.service.Implement.CommentService;
import org.hcv.chodoido_ute_service.service.Implement.ProductService;
import org.hcv.chodoido_ute_service.service.Implement.UserService;
import org.springframework.stereotype.Component;

@Component("customSecurity")
public class CustomSecurity {
    private final UserService userService;
    private final ProductService productService;
    private final CommentService commentService;

    public CustomSecurity(UserService userService, ProductService productService, CommentService commentService) {
        this.userService = userService;
        this.productService = productService;
        this.commentService = commentService;
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
}

