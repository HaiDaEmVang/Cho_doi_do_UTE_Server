package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.Mission;
import org.hcv.chodoido_ute_service.entity.MissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("select count(m) > 0 from Mission m where m.missionType = :missionType AND  m.name like %:name%")
    boolean findByNameAndMissionType(@Param("name") String name, @Param("missionType") MissionType missionType);

    @Query("select count(m) > 0 from Mission m where m.name like :name")
    boolean isExitByName(@Param("name") String name);

}
