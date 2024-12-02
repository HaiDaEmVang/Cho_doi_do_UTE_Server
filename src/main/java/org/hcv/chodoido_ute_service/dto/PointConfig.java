package org.hcv.chodoido_ute_service.dto;

public enum PointConfig {
    DANG_BAI(100),
    DON_THANHCONG(200),
    NHIEMVU_THEODOI(20),
    NHIEMVU_GIOITHIEU(150),
    NHIEMVU_BINHLUAN(30),
    BINHLUAT_TREN3SAO(30),
    THUONG_HANG(100),
    BINHLUAT_DUOI3SAO(-20),
    DON_THATBAT(-50),
    QUA_DICHVU_1(-200),
    QUA_DICHVU_2(-300),
    QUA_DICHVU_3(-400);



    int point;
    PointConfig(int point) {
        this.point = point;
    }
    public int getPoint() {
        return point;
    }
}
