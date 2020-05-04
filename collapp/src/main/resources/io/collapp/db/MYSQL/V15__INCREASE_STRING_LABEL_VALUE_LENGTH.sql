--
-- This file is part of collapp.
--
-- collapp is free software: you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation, either version 3 of the License, or
-- (at your option) any later version.
--
-- collapp is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with collapp.  If not, see <http://www.gnu.org/licenses/>.
--

ALTER TABLE LA_CARD_LABEL_VALUE MODIFY CARD_LABEL_VALUE_STRING VARCHAR(128);

DROP FUNCTION LA_FUN_CARD_LABEL_VALUE_ENFORCE;

DELIMITER //

CREATE FUNCTION LA_FUN_CARD_LABEL_VALUE_ENFORCE (
    CARD_LABEL_VALUE_TYPE ENUM('NULL', 'STRING', 'TIMESTAMP', 'INT', 'CARD', 'USER', 'LIST'),
    CARD_LABEL_VALUE_STRING VARCHAR(128),
    CARD_LABEL_VALUE_TIMESTAMP TIMESTAMP,
    CARD_LABEL_VALUE_INT INTEGER,
    CARD_LABEL_VALUE_CARD_FK INTEGER,
    CARD_LABEL_VALUE_USER_FK INTEGER,
    CARD_LABEL_VALUE_LIST_VALUE_FK INTEGER)
    RETURNS BOOLEAN
    DETERMINISTIC
    BEGIN
        RETURN (CARD_LABEL_VALUE_TYPE IN ('NULL', 'STRING', 'TIMESTAMP', 'INT', 'CARD', 'USER', 'LIST')) AND (CASE
            WHEN CARD_LABEL_VALUE_TYPE = 'NULL' THEN
              CARD_LABEL_VALUE_STRING 	IS NULL AND
              CARD_LABEL_VALUE_TIMESTAMP 	IS NULL AND
              CARD_LABEL_VALUE_INT 		IS NULL AND
              CARD_LABEL_VALUE_CARD_FK	IS NULL AND
              CARD_LABEL_VALUE_USER_FK	IS NULL AND
              CARD_LABEL_VALUE_LIST_VALUE_FK IS NULL
            WHEN CARD_LABEL_VALUE_TYPE = 'STRING' THEN
              CARD_LABEL_VALUE_STRING 	IS NOT NULL AND
              CARD_LABEL_VALUE_TIMESTAMP 	IS NULL AND
              CARD_LABEL_VALUE_INT 		IS NULL AND
              CARD_LABEL_VALUE_CARD_FK	IS NULL AND
              CARD_LABEL_VALUE_USER_FK	IS NULL AND
              CARD_LABEL_VALUE_LIST_VALUE_FK IS NULL
            WHEN CARD_LABEL_VALUE_TYPE = 'TIMESTAMP' THEN
              CARD_LABEL_VALUE_STRING 	IS NULL AND
              CARD_LABEL_VALUE_TIMESTAMP 	IS NOT NULL AND
              CARD_LABEL_VALUE_INT 		IS NULL AND
              CARD_LABEL_VALUE_CARD_FK	IS NULL AND
              CARD_LABEL_VALUE_USER_FK	IS NULL AND
              CARD_LABEL_VALUE_LIST_VALUE_FK IS NULL
            WHEN CARD_LABEL_VALUE_TYPE = 'INT' THEN
              CARD_LABEL_VALUE_STRING 	IS NULL AND
              CARD_LABEL_VALUE_TIMESTAMP 	IS NULL AND
              CARD_LABEL_VALUE_INT 		IS NOT NULL AND
              CARD_LABEL_VALUE_CARD_FK	IS NULL AND
              CARD_LABEL_VALUE_USER_FK	IS NULL AND
              CARD_LABEL_VALUE_LIST_VALUE_FK IS NULL
            WHEN CARD_LABEL_VALUE_TYPE = 'CARD' THEN
              CARD_LABEL_VALUE_STRING 	IS NULL AND
              CARD_LABEL_VALUE_TIMESTAMP 	IS NULL AND
              CARD_LABEL_VALUE_INT 		IS NULL AND
              CARD_LABEL_VALUE_CARD_FK	IS NOT NULL AND
              CARD_LABEL_VALUE_USER_FK	IS NULL AND
              CARD_LABEL_VALUE_LIST_VALUE_FK IS NULL
            WHEN CARD_LABEL_VALUE_TYPE = 'USER' THEN
              CARD_LABEL_VALUE_STRING 	IS NULL AND
              CARD_LABEL_VALUE_TIMESTAMP 	IS NULL AND
              CARD_LABEL_VALUE_INT 		IS NULL AND
              CARD_LABEL_VALUE_CARD_FK	IS NULL AND
              CARD_LABEL_VALUE_USER_FK	IS NOT NULL AND
              CARD_LABEL_VALUE_LIST_VALUE_FK IS NULL
            WHEN CARD_LABEL_VALUE_TYPE = 'LIST' THEN
              CARD_LABEL_VALUE_STRING 	IS NULL AND
              CARD_LABEL_VALUE_TIMESTAMP 	IS NULL AND
              CARD_LABEL_VALUE_INT 		IS NULL AND
              CARD_LABEL_VALUE_CARD_FK	IS NULL AND
              CARD_LABEL_VALUE_USER_FK	IS NULL AND
              CARD_LABEL_VALUE_LIST_VALUE_FK	IS NOT NULL
            END);
    END//



