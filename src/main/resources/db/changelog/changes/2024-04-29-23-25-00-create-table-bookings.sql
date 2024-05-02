--liquibase formatted sql
-- changeset k.zhansaya:1
CREATE TABLE BOOKINGS
(
    ID BIGSERIAL,
    STATUS VARCHAR(100),
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP,
    USER_ID BIGSERIAL,
    EVENT_ID BIGSERIAL,
    NUMBER_OF_RESERVED_SEATS BIGINT,
        CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
        CONSTRAINT FK_EVENT FOREIGN KEY (EVENT_ID) REFERENCES EVENTS(ID)
);

-- changeset k.zhansaya:2
INSERT INTO BOOKINGS(USER_ID, EVENT_ID, NUMBER_OF_RESERVED_SEATS, STATUS)
VALUES (1, 1, 2, 'Подтверждено'),
       (1, 2, 3, 'Подтверждено');