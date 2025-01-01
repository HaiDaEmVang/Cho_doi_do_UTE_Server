use database_chodoido_ute;

SET GLOBAL event_scheduler = ON;
SET SQL_SAFE_UPDATES = 0;

-- cập nhật nhiệm vụ điểm danh theo ngày
CREATE EVENT update_mission_day
ON SCHEDULE EVERY 1 DAY
STARTS '2024-10-24 12:00:00'
DO
  UPDATE mission_details
  set date_checked = null 
  where (Datediff(CURRENT_DATE, DATE_CHECKED) > 1 AND 
  mission_id in (select id from mission where name like '%Điểm danh ngày%'));


insert into category(name, url) values
('Quà tặng','https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/ic_cho_tang_mien_phi.png'),
('Giáo trình tài liệu', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/book.png'),
('Đồ dùng sinh hoạt', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/thiet-bi.png'),
('Linh kiện điện tử', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/linh_kien_dien_tu.png'),
('Dụng cụ học tập', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/dung_cu_hoc_tap.png'),
('Đồ án, bài tập lớn', 'https://chodoidoute.s3.ap-southeast-1.amazonaws.com/images/category/device.png');

INSERT INTO service_package (name, description, time, point, count_post) VALUES
('Dịch vụ 1', 'Khi người dùng đổi 100.000 điểm sẽ được 3 lượt đăng bài.', 2592000000, 20, 3),
('Dịch vụ 2', 'Khi người dùng đổi 100.000 điểm sẽ được 3 lượt đăng bài.', 2592000000, 50, 5),
('Dịch vụ 3', 'Khi người dùng đổi 100.000 điểm sẽ được 3 lượt đăng bài.', 2592000000, 70, 7);


DELIMITER $$


CREATE TRIGGER insertBuy
BEFORE INSERT ON buy
FOR EACH ROW
BEGIN
    DECLARE b INT;

    SELECT p.count INTO b
    FROM product p
    WHERE p.id = NEW.id_product;
    
    IF (b - New.count) < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Product is out of stock or does not exist';
    ELSE
        UPDATE product
        SET count = count - New.count
        WHERE id = NEW.id_product;
    END IF;
END;


CREATE TRIGGER updateProduct
after update ON product
FOR EACH ROW
BEGIN
    IF NEW.count = 0  THEN
        UPDATE product
        SET post_product_status = 'DA_AN'
        WHERE id = NEW.id;
    END IF;
END ;

CREATE TRIGGER addComment
AFTER INSERT ON comment
FOR EACH ROW
BEGIN
    -- Kiểm tra xem người dùng đã mua sản phẩm hay chưa
    IF (SELECT COUNT(id) 
        FROM buy 
        WHERE buy.id_product = NEW.id_product 
          AND buy.id_user_buy = NEW.id_user
          AND buy.status = 'THANH_CONG') = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'User must purchase the product before commenting';
    END IF;

    -- Cập nhật trạng thái isComment thành true
    UPDATE buy 
    SET is_comment = 1
    WHERE buy.id_product = NEW.id_product  
      AND id_user_buy = NEW.id_user;
END ;




DELIMITER ;
