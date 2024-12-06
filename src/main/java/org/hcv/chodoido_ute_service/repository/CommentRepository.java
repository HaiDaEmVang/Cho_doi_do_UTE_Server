package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select b from Comment b where b.product.user = :user")
    List<Comment> findCommentByUser(@Param("user") User user);

    @Query("select b from Comment b where b.product = :product")
    List<Comment> findCommentByProduct(@Param("product") Product product);

    @Query("select b from Comment b where b.user = :user")
    List<Comment> findCommentedByUser(@Param("user") User user);

    @Query("select c from Comment c where c.user = :user and c.product = :product")
    Comment existsCommentByUserAndProduct(@Param("user") User user, @Param("product") Product product);

}
