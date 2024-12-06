package org.hcv.chodoido_ute_service.mapper;

import org.hcv.chodoido_ute_service.dto.response.FollowDTO;
import org.hcv.chodoido_ute_service.entity.Follower;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface FollowMapper {
    FollowDTO toFollowDTO(Follower follower) ;
}
