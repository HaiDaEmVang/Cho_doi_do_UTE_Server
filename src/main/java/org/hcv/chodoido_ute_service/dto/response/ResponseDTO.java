package org.hcv.chodoido_ute_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hcv.chodoido_ute_service.exception.Error;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  ResponseDTO<T> {
    String status;
    T data;
    Error error;
    String message;
}
