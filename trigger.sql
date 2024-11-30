DELIMITER $$

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
END $$

DELIMITER ;
