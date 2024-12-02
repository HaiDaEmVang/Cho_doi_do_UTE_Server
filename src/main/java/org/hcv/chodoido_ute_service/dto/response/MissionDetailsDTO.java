package org.hcv.chodoido_ute_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionDetailsDTO {
    LocalDate missionDate;
    MissionDTO mission;
}
