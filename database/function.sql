USE `anliantest_db2`;
DROP function IF EXISTS `num2str`;

DELIMITER $$
USE `anliantest_db2`$$
CREATE FUNCTION `num2str`(val double, scale int, numType varchar(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
	declare ret varchar(255);
	set ret = REPLACE(FORMAT(val,scale),',','');
    if (numType<>'=') then
		set ret = concat(numType, ret);
    end if;
RETURN ret;
END
$$

DELIMITER ;
