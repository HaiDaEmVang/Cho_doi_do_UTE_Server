package org.hcv.chodoido_ute_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
        Long id;
        String email;
        String name;
        String nickName;
        String password;
        Boolean gender;
        String facebook ;
        String zalo;
        String local;
}
