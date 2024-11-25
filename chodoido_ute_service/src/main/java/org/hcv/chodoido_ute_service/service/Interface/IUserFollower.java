package org.hcv.chodoido_ute_service.service.Interface;


import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.Follower;

import java.util.List;

public interface IUserFollower {
     Follower addFollower(Long idFollow, Long idFollower);
     void removeFollower(Long idFollow, Long idFollower);
     List<UserDTO> findListUserFollowers(Long idUserFollow);
     int countFollowers(Long idUserFollow);
}
