use hotel_managment_system;

SELECT * FROM room;

-- get available rooms
SELECT 
	room_id, is_available,
    type_name, capacity,
    rate, description
FROM room r 
JOIN room_type rt ON r.type_id = rt.type_id
WHERE is_available = 1;

-- get room amenities given room type
SELECT 
	rt.type_id, ra.amenity_id ,type_name,
    amenity_name, ra.description
FROM room_type rt
JOIN room_type_amenity rtm USING (type_id)
JOIN room_amenity ra USING (amenity_id)
WHERE type_id = 1;

-- get menu
SELECT 
	*
FROM menu;

-- get available table 
SELECT * FROM `table`
WHERE is_available =1;

-- get special_request
SELECT * FROM special_request;

-- get invoices
SELECT * FROM invoice;