package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.Mission;
import org.hcv.chodoido_ute_service.entity.MissionDetails;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface MissionDetailsRepository extends JpaRepository<MissionDetails, Long> {
    @Query("select md from MissionDetails md where md.user.id = :idUser and md.mission.id =:idMission")
    MissionDetails findByIdUserAndIdMission(@Param("idUser") Long idUser, @Param("idMission") Long idMission);

    @Query("select md from MissionDetails md where md.user.id = :idUser and md.mission.name like '%Điểm danh%' and md.dateChecked is not null order by md.dateChecked desc")
    List<MissionDetails> getMissionSign(@Param("idUser") Long idUser);

    @Query("select  md from MissionDetails md where md.user.id =:idUser and md.mission.name like '%Điểm danh%'")
    List<MissionDetails> findListMissionSignByIdUser(@Param("idUser") Long idUser);
}
