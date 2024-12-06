package org.hcv.chodoido_ute_service.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class MissionDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    LocalDate dateChecked;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    Mission mission;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
}
