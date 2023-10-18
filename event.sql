
SHOW VARIABLES LIKE 'event_scheduler';

DELIMITER //

CREATE EVENT my_event
  ON SCHEDULE
    EVERY 1 DAY
    STARTS CURRENT_TIMESTAMP
  DO BEGIN
    -- Event body: Add your desired SQL statements or procedure calls here
    UPDATE room 
    SET is_available = 1
    WHERE check_out_date <= current_timestamp();
    
END //

DELIMITER ;
    