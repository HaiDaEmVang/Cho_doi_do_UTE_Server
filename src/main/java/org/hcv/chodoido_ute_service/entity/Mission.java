package org.hcv.chodoido_ute_service.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Mission {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Long point;
    @Enumerated(EnumType.STRING)
    MissionType missionType;
    Boolean isMissionDefault;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mission", cascade = CascadeType.REMOVE)
    List<MissionDetails> missionDetailsList = new ArrayList<>();
}
