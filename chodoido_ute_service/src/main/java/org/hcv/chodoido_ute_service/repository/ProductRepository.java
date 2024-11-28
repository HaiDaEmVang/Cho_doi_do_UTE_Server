package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.Category;
import org.hcv.chodoido_ute_service.entity.PostProductStatus;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("select u from Product u where u.count > 0 and u.postProductStatus =:status")
    List<Product> findProductView(@Param("status") PostProductStatus postProductStatus);

    @Query("select u from Product  u where  u.count > 0 and u.postProductStatus = :status and u.category = :category")
    List<Product> findByCategory(@Param("category") Category category, @Param("status") PostProductStatus status);

    List<Product> findByUser(User user);

    @Query("select u from Product u where  u.count > 0 and u.postProductStatus = :status and u.title like :name")
    List<Product> findByTitle(@Param("name") String name, @Param("status") PostProductStatus status);

    @Query("select u from Product u where u.postProductStatus = :postProductStatus")
    List<Product> findByPostProductStatus(@Param("postProductStatus") PostProductStatus postProductStatus);

    @Query("select u from Product  u where u.postProductStatus = :postProductStatus and u.user =:user")
    List<Product> findByUserAndPostProductStatus(@Param("postProductStatus") PostProductStatus postProductStatus, @Param("user") User user);

}
