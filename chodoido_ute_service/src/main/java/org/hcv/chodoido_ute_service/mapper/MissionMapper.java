package org.hcv.chodoido_ute_service.mapper;

import org.hcv.chodoido_ute_service.dto.request.MissionRequest;
import org.hcv.chodoido_ute_service.dto.response.BuyDTO;
import org.hcv.chodoido_ute_service.dto.response.MissionDTO;
import org.hcv.chodoido_ute_service.entity.Buy;
import org.hcv.chodoido_ute_service.entity.Mission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface MissionMapper {
    MissionDTO toMissionDto(Mission mission) ;
    void updateMission(MissionRequest missionRequest, @MappingTarget Mission mission) ;
}
