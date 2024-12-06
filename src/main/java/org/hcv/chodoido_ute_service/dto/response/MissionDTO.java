package org.hcv.chodoido_ute_service.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hcv.chodoido_ute_service.entity.MissionType;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {
    Long id;
    String name;
    String description;
    Long point;
    String missionType;
    LocalDate dateChecked;
    UserDTO user;

}
