package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.MissionRequest;
import org.hcv.chodoido_ute_service.dto.response.MissionDTO;
import org.hcv.chodoido_ute_service.dto.response.MissionDetailsDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.*;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.MissionMapper;
import org.hcv.chodoido_ute_service.mapper.UserMapper;
import org.hcv.chodoido_ute_service.repository.MissionDetailsRepository;
import org.hcv.chodoido_ute_service.repository.MissionRepository;
import org.hcv.chodoido_ute_service.repository.UserRepository;
import org.hcv.chodoido_ute_service.service.Interface.IMissionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MissionService implements IMissionService {
    MissionRepository missionRepository;
    MissionDetailsRepository missionDetailsRepository;
    UserRepository userRepository;
    MissionMapper missionMapper;
    UserMapper userMapper;


    @Override
    public List<MissionDTO> findAll() {
        return missionRepository.findAll()
                .stream().map(missionMapper::toMissionDto).toList();
    }

    @Override
    public MissionDTO findById(Long id) {
        return missionMapper.toMissionDto(missionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mission not found")));
    }

    @Override
    public Mission findMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mission not found"));
    }

    @Override
    public MissionDTO update(MissionRequest missionRequest) {
        Mission m = this.findMissionById(missionRequest.getId());
        missionMapper.updateMission( missionRequest, m);
        return missionMapper.toMissionDto(missionRepository.save(m));
    }

    @Override
    public MissionDTO add(MissionRequest missionRequest) {
        Mission m = new Mission();
        missionMapper.updateMission(missionRequest, m);

        Mission missionSaved = missionRepository.save(m);
        if(!add(missionSaved))
            log.error("Can not add mission details");
        return missionMapper.toMissionDto(missionRepository.save(missionSaved));
    }
// ăn chi mà code ngu ri ('.') reẻng thì sửa logic phân naày lại :<<<
    public boolean add(Mission mission){
        List<User> users = userRepository.findAll();

        List<MissionDetails> missionDetails = new ArrayList<>();
        if(!users.isEmpty()){
            for(User user : users){
                MissionDetails missionDetail = MissionDetails.builder().id(0L).dateChecked(null).user(user).mission(mission).build();
                MissionDetails savedMissionDetail = missionDetailsRepository.save(missionDetail);
                missionDetails.add(savedMissionDetail);
            }
        }
        return !missionDetails.isEmpty();
    }

    public void addMissionToUser(User user){
        List<Mission> missions = missionRepository.findAll();
        if(!missions.isEmpty()){
            for(Mission mission : missions){
                MissionDetails missionDetail = MissionDetails.builder().id(0L).dateChecked(null).user(user).mission(mission).build();
                missionDetailsRepository.save(missionDetail);
            }
        }
    }

    @Override
    public List<MissionDTO> addList(List<MissionRequest> missionRequest) {
        return missionRequest.stream().map(this::add).toList();
    }

    @Override
    public MissionDTO updateUser(Long idMission, Long idUser) {
        return null;
    }

    @Override
    public void delete(Long id) {
        missionRepository.deleteById(id);
    }

    @Override
    public UserDTO successMisstion(Long idUser, Long idMission) {
        MissionDetails  missionDetails = missionDetailsRepository.findByIdUserAndIdMission(idUser, idMission);
        if(missionDetails.getMission().getMissionType() == MissionType.NGAY)
            missionDetails.setDateChecked(LocalDate.now());
        missionDetails.getUser().setPoint(missionDetails.getMission().getPoint());
        missionDetailsRepository.save(missionDetails);
        return userMapper.userToUserDTO(missionDetailsRepository.save(missionDetails).getUser());
    }

    @Override
    public MissionDetailsDTO getDateSign(Long idUser) {
        List<MissionDetails> missionDetails = missionDetailsRepository.getMissionSign(idUser);
        if(missionDetails == null || missionDetails.isEmpty())
            throw new NotFoundException("Mission not found");
        return MissionDetailsDTO.builder()
                .missionDate(missionDetails.get(0).getDateChecked()).mission(missionMapper.toMissionDto(missionDetails.get(0).getMission())).build();
    }



}
