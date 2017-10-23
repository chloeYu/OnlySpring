DELIMITER $$
DROP PROCEDURE IF EXISTS insert_comment $$

CREATE PROCEDURE insert_comment(IN vref_id int(11), in vre_level int(11), in vuserid varchar(20), in vtext varchar(1000))
BEGIN
	declare C_LEVEL int;
	set C_LEVEL = 0;

	IF vre_level=1 THEN
		select (re_level+1) into C_LEVEL from comments where cid = vref_id;
	end if;
    
    insert into Comments (ref_id, re_level, userid, text, likes, created, modified, del) 
    values (vref_id, C_LEVEL, vuserid, vtext, 0, now(), now(), 'n');
END$$
DELIMITER ;


DELIMITER $$
DROP PROCEDURE IF EXISTS insert_or_update $$

CREATE PROCEDURE insert_or_update(IN vuserid varchar(20), in vlid int(11), in vtype int(11))
BEGIN
	declare L_TYPE char(1);
    declare L_COUNT int;
	set L_TYPE = '';
    set L_COUNT = 0;
    
    SELECT status INTO L_TYPE from likes WHERE userid = vuserid and lid = vlid;
    
    IF vtype = 0 THEN
		select likes into L_COUNT from post where pid = vlid;
	ELSEIF vtype=1 THEN
		select likes into L_COUNT from comments where cid = vlid;
	end if;
    
    IF L_TYPE = '' THEN
		insert into likes values (vlid, vuserid, 'y', vtype);
        IF vtype = 0 THEN
			update post set likes = L_COUNT+1 where pid = vlid;
		ELSEIF vtype=1 THEN
			update comments set likes = L_COUNT+1 where cid = vlid;
		end if;
	ELSEIF L_TYPE = 'y' THEN
		update likes set status = 'n' where userid = vuserid and lid = vlid;
        IF vtype = 0 THEN
			update post set likes = L_COUNT-1 where pid = vlid;
		ELSEIF vtype=1 THEN
			update comments set likes = L_COUNT-1 where cid = vlid;
		end if;
	ELSEIF L_TYPE = 'n' THEN
		update likes set status = 'y' where userid = vuserid and lid = vlid;
        IF vtype = 0 THEN
			update post set likes = L_COUNT+1 where pid = vlid;
		ELSEIF vtype=1 THEN
			update comments set likes = L_COUNT+1 where cid = vlid;
		end if;
	end if;
END$$
DELIMITER ;

desc alert;
delete from post where pid = 116;
delete from alert where url = 116;

select * from post ;
select * from alert;
call insert_post('test', 'ynnnnnn');
DELIMITER $$
DROP PROCEDURE IF EXISTS insert_post $$

CREATE PROCEDURE insert_post(in vpid int(11), in vuserid varchar(20), in vtype varchar(7))
BEGIN
	DECLARE done INT DEFAULT 0;
	DECLARE friend varchar(20);
	DECLARE
	c CURSOR FOR select a.userid from (select m.* from member m join follow f on (f.uid1 = m.userid or f.uid2 = m.userid) where (f.status = 1 and (f.uid1=vuserid	 or f.uid2=vuserid ))) a where a.userid != vuserid;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
    insert into POST values (vpid, vuserid, vtype, 0, now(), now(),'n');
	
	OPEN c;
	read_loop:
	LOOP                                      
	FETCH c INTO friend;
		IF done = 1 THEN
            LEAVE read_loop;
		END IF;
     INSERT INTO alert(type, status, uid1, uid2, url, created)
     VALUES (0, 'n', vuserid, friend, 'post/'+vpid, now());

 END LOOP;    
 CLOSE c;
                   
	
END$$
DELIMITER ;
