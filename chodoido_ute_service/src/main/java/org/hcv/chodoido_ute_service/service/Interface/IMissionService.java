package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.dto.request.MissionRequest;
import org.hcv.chodoido_ute_service.dto.response.MissionDTO;
import org.hcv.chodoido_ute_service.dto.response.MissionDetailsDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.Mission;
import org.hcv.chodoido_ute_service.entity.MissionDetails;

import java.util.List;

public interface IMissionService {
    List<MissionDTO> findAll();
    MissionDTO findById(Long id);
    Mission findMissionById(Long id);
    MissionDTO update(MissionRequest missionRequest);
    MissionDTO add(MissionRequest missionRequest);
    List<MissionDTO> addList(List<MissionRequest> missionRequest);
    MissionDTO updateUser(Long idMission, Long idUser);

    void delete(Long id);

    UserDTO successMisstion(Long idUser, Long idMission);
    MissionDetailsDTO getDateSign(Long idUser);
}
