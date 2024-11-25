package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.Follower;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface FollowerRepository extends JpaRepository<Follower, Long> {

    @Query("select f.userFollower from Follower f where f.userFollow.id = :idUser")
    List<User> listFollowerByUser(@Param("idUser") Long idUser);

    @Query("select count(f.userFollower) from Follower f where f.userFollow.id = :idUser")
    int countFollowerByUser(@Param("idUser") Long idUser);

    Optional<Follower> findByUserFollowAndUserFollower(User userFollow, User userFollower);
}
