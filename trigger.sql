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
END $$

CREATE TRIGGER updateProduct
after update ON product
FOR EACH ROW
BEGIN
    IF NEW.count = 0  THEN
        UPDATE product
        SET post_product_status = 'DA_AN'
        WHERE id = NEW.id;
    END IF;
END $$


CREATE EVENT update_mission_day
ON SCHEDULE EVERY 1 DAY
STARTS '2024-10-24 12:00:00'
DO
  UPDATE mission_details
  set date_checked = null 
  where (Datediff(CURRENT_DATE, DATE_CHECKED) > 1 AND mission_id in (select id from mission where name like '%Điểm danh ngày%')) or mission_id  = (select id from mission where name = 'Điểm danh ngày 8')
  
  
  drop event update_mission_day
DELIMITER ;
