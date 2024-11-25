package org.hcv.chodoido_ute_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hcv.chodoido_ute_service.entity.MissionType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionRequest {
    Long id;
    String name;
    String description;
    Long point;
    MissionType missionType;
}
