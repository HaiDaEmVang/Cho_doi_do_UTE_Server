package org.hcv.chodoido_ute_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDTO {
    Long id;
    UserDTO userFollow;
    UserDTO userFollower;

}
