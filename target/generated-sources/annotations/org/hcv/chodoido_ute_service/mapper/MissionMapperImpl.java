package org.hcv.chodoido_ute_service.mapper;

import javax.annotation.processing.Generated;
import org.hcv.chodoido_ute_service.dto.request.MissionRequest;
import org.hcv.chodoido_ute_service.dto.response.MissionDTO;
import org.hcv.chodoido_ute_service.entity.Mission;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class MissionMapperImpl implements MissionMapper {

    @Override
    public MissionDTO toMissionDto(Mission mission) {
        if ( mission == null ) {
            return null;
        }

        MissionDTO.MissionDTOBuilder missionDTO = MissionDTO.builder();

        missionDTO.id( mission.getId() );
        missionDTO.name( mission.getName() );
        missionDTO.description( mission.getDescription() );
        missionDTO.point( mission.getPoint() );
        if ( mission.getMissionType() != null ) {
            missionDTO.missionType( mission.getMissionType().name() );
        }

        return missionDTO.build();
    }

    @Override
    public void updateMission(MissionRequest missionRequest, Mission mission) {
        if ( missionRequest == null ) {
            return;
        }

        mission.setId( missionRequest.getId() );
        mission.setName( missionRequest.getName() );
        mission.setDescription( missionRequest.getDescription() );
        mission.setPoint( missionRequest.getPoint() );
        mission.setMissionType( missionRequest.getMissionType() );
    }
}
