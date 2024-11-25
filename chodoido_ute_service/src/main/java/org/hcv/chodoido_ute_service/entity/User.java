package org.hcv.chodoido_ute_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email;
    String name;
    String nickName;
    Boolean gender;
    String imgUrl;
    String password ;
    String facebook ;
    String zalo;
    String local;
    Long productLost;
    Long productSuccess;
    Long point;
    Long countPost;

    @Enumerated(EnumType.STRING)
    Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ServiceDetails> serviceDetails = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Product> productList = new ArrayList<>();

//    @JsonManagedReference
//    @OneToMany(mappedBy = "userFollow", fetch = FetchType.LAZY)
//    List<Follower> userFollowList ;

    @JsonManagedReference
    @OneToMany(mappedBy = "userFollower", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<Follower> userFollowerList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    List<Buy> buyList =new ArrayList<>();


    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    List<MissionDetails> MissionDetailList =new ArrayList<>();

    public void setProductLost() {
        if(this.productLost == null)
            this.productLost = 0L;
        else
        this.productLost =
                productList.stream().filter(item -> item.getPostProductStatus() == PostProductStatus.DA_TUCHOI).count();
    }

    public void setProductSuccess() {
        if(this.productSuccess == null)
            this.productSuccess = 0L;
        else
        this.productLost =
                productList.stream().filter(item -> item.getPostProductStatus() == PostProductStatus.DA_DUYET).count();
    }

    public void setPoint(Long point){
        if(this.point == null)
            this.point = 0L;
        else this.point += point;
    }

    public void setCountPost(Long count){
        if(this.countPost == null)
            this.countPost = 0L;
        else
            this.countPost += count;
    }

}
