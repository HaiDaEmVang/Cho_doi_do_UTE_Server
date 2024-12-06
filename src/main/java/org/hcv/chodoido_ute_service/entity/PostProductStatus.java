package org.hcv.chodoido_ute_service.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum PostProductStatus {
    CHO_DUYET("Chờ duyệt"),
    DA_DUYET("Đã duyệt"),
    DA_TUCHOI("Đã từ chối"),
    DA_AN("Đã ẩn");

    private String description;


}
