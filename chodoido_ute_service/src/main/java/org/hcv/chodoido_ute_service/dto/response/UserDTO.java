package org.hcv.chodoido_ute_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.entity.Role;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserDTO {
    Long id;
    String email;
    String name;
    String nickName;
    Boolean gender;
    String imgUrl;
    String facebook ;
    String zalo;
    String local;
    Long productLost;
    Long productSuccess;
    Long point;
    Long countPost;
    Role role;
}
