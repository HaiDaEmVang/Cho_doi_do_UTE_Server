package org.hcv.chodoido_ute_service.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Buy {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "id_Product")
    Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "id_User_Buy")
    User user;

    LocalDate timeBuy;
    Long count = 0L;
    Double price;


    @Enumerated(EnumType.STRING)
    BuyStatus status;
}
