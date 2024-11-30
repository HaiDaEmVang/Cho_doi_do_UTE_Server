create database database_chodoido_ute;
drop database database_chodoido_ute;
use database_chodoido_ute;

-- make schedule
SET GLOBAL event_scheduler = ON;
SET SQL_SAFE_UPDATES = 0;

-- CREATE EVENT DeleteUser
-- ON SCHEDULE EVERY 30 second
-- STARTS '2024-10-27 12:00:00'
-- DO
--   -- query;


select * from user;

delete from user where id != ;
UPDATE user
SET count_post = 4
where id = 5;


INSERT INTO user (email, name, nick_name, gender, img_url, password, facebook, zalo, local, product_lost, product_success, point, role)
VALUES 
('22115053122102@sv.ute.udn.vn', 'LÊ ĐỨC TUẤN ANH', 'Tuấn Anh', 1, '', '12345678', '', '', 'QUẢNG NGÃI', 0, 0, 0, 'USER'),
('22115053122103@sv.ute.udn.vn', 'TRẦN VĂN BÌNH', 'Văn Bình', 1, '', '12345678', '', '', 'QUẢNG NAM', 0, 0, 0, 'USER'),
('22115053122105@sv.ute.udn.vn', 'ĐOÀN CAO CƯỜNG', 'Cao Cường', 1, '', '12345678', '', '', 'THỪA THIÊN HUẾ', 0, 0, 0, 'USER'),
('22115053122107@sv.ute.udn.vn', 'NGUYỄN VĂN THẾ DINH', 'Thế Dinh', 1, '', '12345678', '', '', 'ĐẮK LẮK', 0, 0, 0, 'USER'),
('22115053122109@sv.ute.udn.vn', 'ĐẶNG NGỌC DŨNG', 'Ngọc Dũng', 1, '', '12345678', '', '', 'NGHỆ AN', 0, 0, 0, 'USER'),
('22115053122110@sv.ute.udn.vn', 'TRẦN NGUYỄN THANH DƯƠNG', 'Thanh Dương', 1, '', '12345678', '', '', 'QUẢNG TRỊ', 0, 0, 0, 'USER'),
('22115053122111@sv.ute.udn.vn', 'LÊ THỊ TRÀ GIANG', 'Trà Giang', 0, '', '12345678', '', '', 'HÀ TĨNH', 0, 0, 0, 'USER'),
('22115053122112@sv.ute.udn.vn', 'LÊ NGỌC HẢI', 'Ngọc Hải', 1, '', '12345678', '', '', 'QUẢNG TRỊ', 0, 0, 0, 'USER'),
('22115053122113@sv.ute.udn.vn', 'TRẦN CÔNG HIẾU', 'Công Hiếu', 1, '', '12345678', '', '', 'THỪA THIÊN HUẾ', 0, 0, 0, 'USER'),
('22115053122114@sv.ute.udn.vn', 'NGUYỄN THỊ HIẾU', 'Thị Hiếu', 0, '', '12345678', '', '', 'QUẢNG NAM', 0, 0, 0, 'USER'),
('22115053122115@sv.ute.udn.vn', 'PHẠM THANH HIẾU', 'Thanh Hiếu', 1, '', '12345678', '', '', 'ĐÀ NẴNG', 0, 0, 0, 'USER'),
('22115053122117@sv.ute.udn.vn', 'PHẠM LÊ VĂN HUY', 'Lê Văn Huy', 1, '', '12345678', '', '', 'ĐÀ NẴNG', 0, 0, 0, 'USER'),
('22115053122118@sv.ute.udn.vn', 'NGUYỄN VŨ KHANH', 'Vũ Khanh', 1, '', '12345678', '', '', 'ĐÀ NẴNG', 0, 0, 0, 'USER'),
('22115053122119@sv.ute.udn.vn', 'ĐỖ HÙNG QUỐC KHÁNH', 'Quốc Khánh', 1, '', '12345678', '', '', 'QUẢNG NAM', 0, 0, 0, 'USER'),
('22115053122120@sv.ute.udn.vn', 'NGUYỄN THỊ THU LIÊN', 'Thu Liên', 0, '', '12345678', '', '', 'QUẢNG TRỊ', 0, 0, 0, 'USER'),
('22115053122121@sv.ute.udn.vn', 'LÊ NGUYỄN THÀNH LINH', 'Thành Linh', 1, '', '12345678', '', '', 'QUẢNG TRỊ', 0, 0, 0, 'USER'),
('22115053122122@sv.ute.udn.vn', 'TRẦN PHƯỚC LỘC', 'Phước Lộc', 1, '', '12345678', '', '', 'ĐÀ NẴNG', 0, 0, 0, 'USER'),
('22115053122123@sv.ute.udn.vn', 'ĐẶNG THANH MAI', 'Thanh Mai', 0, '', '12345678', '', '', 'ĐÀ NẴNG', 0, 0, 0, 'USER'),
('22115053122124@sv.ute.udn.vn', 'NGUYỄN THỊ BÍCH NGỌC', 'Bích Ngọc', 0, '', '12345678', '', '', 'KON TUM', 0, 0, 0, 'USER'),
('22115053122125@sv.ute.udn.vn', 'PHẠM THỊ THU NGUYỆT', 'Thu Nguyệt', 0, '', '12345678', '', '', 'BÌNH ĐỊNH', 0, 0, 0, 'USER');



select * from category;

insert into category(name, url) values
('Quà tặng','https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/ic_cho_tang_mien_phi.png'),
('Giáo trình tài liệu', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/book.png'),
('Đồ dùng sinh hoạt', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/thiet-bi.png'),
('Linh kiện điện tử', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/linh_kien_dien_tu.png'),
('Dụng cụ học tập', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/dung_cu_hoc_tap.png'),
('Đồ án, bài tập lớn', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/device.png');



select * from follower;
delete from follower where id <>11;

INSERT INTO follower (id_user, id_user_follower) VALUES (1, 2), (1, 3), (1, 4), (1, 5), (1, 6);
INSERT INTO follower (id_user, id_user_follower) VALUES (2, 1), (2, 3), (2, 4), (2, 5), (2, 6);
INSERT INTO follower (id_user, id_user_follower) VALUES (3, 1), (3, 2), (3, 5), (3, 6), (3, 7);
INSERT INTO follower (id_user, id_user_follower) VALUES (4, 1), (4, 2), (4, 3), (4, 6), (4, 7);
INSERT INTO follower (id_user, id_user_follower) VALUES (5, 2), (5, 3), (5, 4), (5, 7), (5, 8);
INSERT INTO follower (id_user, id_user_follower) VALUES (6, 1), (6, 4), (6, 5), (6, 8), (6, 9);
INSERT INTO follower (id_user, id_user_follower) VALUES (7, 1), (7, 5), (7, 6), (7, 9), (7, 10);
INSERT INTO follower (id_user, id_user_follower) VALUES (8, 2), (8, 6), (8, 7), (8, 10), (8, 11);
INSERT INTO follower (id_user, id_user_follower) VALUES (9, 3), (9, 7), (9, 8), (9, 11), (9, 12);
INSERT INTO follower (id_user, id_user_follower) VALUES (10, 4), (10, 8), (10, 9), (10, 12), (10, 13);
INSERT INTO follower (id_user, id_user_follower) VALUES (11, 5), (11, 9), (11, 10), (11, 13), (11, 14);
INSERT INTO follower (id_user, id_user_follower) VALUES (12, 6), (12, 10), (12, 11), (12, 14), (12, 15);
INSERT INTO follower (id_user, id_user_follower) VALUES (13, 7), (13, 11), (13, 12), (13, 15), (13, 16);
INSERT INTO follower (id_user, id_user_follower) VALUES (14, 8), (14, 12), (14, 13), (14, 16), (14, 17);
INSERT INTO follower (id_user, id_user_follower) VALUES (15, 9), (15, 13), (15, 14), (15, 17), (15, 18);
INSERT INTO follower (id_user, id_user_follower) VALUES (16, 10), (16, 14), (16, 15), (16, 18), (16, 19);
INSERT INTO follower (id_user, id_user_follower) VALUES (17, 11), (17, 15), (17, 16), (17, 19), (17, 20);
INSERT INTO follower (id_user, id_user_follower) VALUES (18, 12), (18, 16), (18, 17), (18, 20), (18, 1);
INSERT INTO follower (id_user, id_user_follower) VALUES (19, 13), (19, 17), (19, 18), (19, 1), (19, 2);
INSERT INTO follower (id_user, id_user_follower) VALUES (20, 14), (20, 18), (20, 19), (20, 2), (20, 3);

select * from product;

select * from service_package;
select * from service_details;
select * from mission;
select * from mission_details;
select * from category;
select * from product;
select * from buy;
select * from user;
select * from follower;
select * from comment;

SELECT EXISTS(SELECT 1 FROM comment WHERE id_user = 5  AND id_product = 8)
select * from user;
select * from service_package;
select * from service_details;

INSERT INTO service_package (name, description, time, point, count_post) VALUES
('Dịch vụ 1', 'Khi người dùng đổi 100.000 điểm sẽ được 3 lượt đăng bài.', 2592000000, 20, 3),
('Dịch vụ 2', 'Khi người dùng đổi 100.000 điểm sẽ được 3 lượt đăng bài.', 2592000000, 50, 5),
('Dịch vụ 3', 'Khi người dùng đổi 100.000 điểm sẽ được 3 lượt đăng bài.', 2592000000, 70, 7)

select * from product
update product 
set post_product_status = "DA_DUYET"
where id =3 