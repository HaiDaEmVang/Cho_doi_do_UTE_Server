package org.hcv.chodoido_ute_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServicePackageRequest {
    Long id;
    String name;
    String description;
    Long time;
    Long point;
    int countPost; //so luong bai dang
}
