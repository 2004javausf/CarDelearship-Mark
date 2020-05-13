CREATE TABLE USERS(
USER_ID INTEGER PRIMARY KEY,
USER_NAME VARCHAR(50),
USER_PASSWORD VARCHAR(50),
FIRSTNAME VARCHAR2(50),
LASTNAME VARCHAR2(50));

--SEQUENCE FOR UNIQUE ID
CREATE SEQUENCE USERID;


--Procedure
CREATE OR REPLACE PROCEDURE INSERTUSERS(NAME IN VARCHAR2, PASS IN VARCHAR2, FNAME IN VARCHAR2, LNAME IN VARCHAR2)
AS
BEGIN 
INSERT INTO USERS VALUES(USERID.NEXTVAL, NAME, PASS, FNAME, LNAME);
COMMIT;
END;
/

ALTER TABLE USERS
ADD CONSTRAINT UQ_USER_NAME
UNIQUE (USER_ID);

EXECUTE INSERTUSERS('test','test','John','Doe');
EXECUTE INSERTUSERS('testy','testy','Jane','Doe');
SELECT * FROM USERS;


--will need user id and car id later
--RUN ALL ABOVE HERE TO CREATE USERS TABLE WITH VALUES FROM SCRATCH!
-------------------------------------------------------------------------------------------------------------------------------

-- EMPLOYEEE
CREATE TABLE EMPLOYEE(
EMPLOYEE_NAME VARCHAR2(50),
EMPLOYEE_PASSWORD VARCHAR2(50));

INSERT INTO EMPLOYEE VALUES('employee', 'employee');
-------------------------------------------------------------------------------------------------------------------------------

-- LOT
CREATE TABLE LOT(
CAR_ID INTEGER PRIMARY KEY,
MAKE VARCHAR2(50),
MODEL VARCHAR2(50),
COLOR VARCHAR2(50),
MILEAGE NUMBER,
PRICE NUMBER,
YEAR INTEGER);

--SEQUENCE FOR UNIQUE CAR ID
CREATE SEQUENCE CARID;

--ADD CAR TO LOT PROCEDURE
CREATE OR REPLACE PROCEDURE ADDCAR(MAKE IN VARCHAR2, MODEL IN VARCHAR2, COLOR IN VARCHAR2, MILEAGE IN NUMBER, PRICE IN NUMBER, YEAR IN INTEGER)
AS
BEGIN 
INSERT INTO LOT VALUES(CARID.NEXTVAL, MAKE, MODEL, COLOR, MILEAGE,PRICE,YEAR);
COMMIT;
END;
/


--REMOVE FROM LOT PROCEDURE
CREATE OR REPLACE PROCEDURE REMOVECAR(CARIDD IN VARCHAR2)
AS
BEGIN
DELETE FROM OFFERS WHERE CAR_ID = CARIDD;
DELETE FROM LOT WHERE CAR_ID = CARIDD;
COMMIT;
END;
/




EXECUTE ADDCAR('Tesla', 'Model 3', 'Black', 0, 39900,2020);
EXECUTE ADDCAR('Tesla', 'Model 3', 'Red', 0, 39900,2020);
EXECUTE ADDCAR('Lexus', 'Rx 330', 'Silver', 160000, 3000,2006);
EXECUTE ADDCAR('Lexus', 'Rx 330', 'Tan', 160000, 3000,2006);
EXECUTE ADDCAR('Toyota', 'Camry', 'Silver', 300000, 1000, 1999);
EXECUTE ADDCAR('Toyota', 'Camry', 'Silver', 300000, 1000, 1999);
EXECUTE ADDCAR('Toyota', 'Camry', 'Silver', 300000, 1000, 1999);
SELECT * FROM LOT;


-------------------------------------------------------------------------------------------------------------------------------
--OFFER TABLE
CREATE TABLE OFFERS(
CAR_ID INTEGER,
PRICE NUMBER,
USER_ID INTEGER,
OFFER NUMBER,
PAY_TIME INTEGER DEFAULT 10);

--OFFER TABLE PROCEDURE
CREATE OR REPLACE PROCEDURE ADDOFFER(CAR_ID IN INTEGER, PRICE IN NUMBER, USER_ID IN INTEGER, OFFER IN NUMBER, PAY_TIME IN INTEGER)
AS
BEGIN
INSERT INTO OFFERS VALUES(CAR_ID, PRICE, USER_ID, OFFER, PAY_TIME);
COMMIT;
END;
/


--OFFER UPDATE PROCEDURE
CREATE OR REPLACE PROCEDURE UPDATEOFFER(CARID IN INTEGER, USERID IN INTEGER, OFFERING IN NUMBER, PAYTIME IN INTEGER)
AS
BEGIN
UPDATE OFFERS
SET OFFER = OFFERING, PAY_TIME = PAYTIME
WHERE CAR_ID = CARID
        AND USER_ID = USERID;
COMMIT;
END;
/

--OFFER CONSTRAINTS
ALTER TABLE OFFERS
ADD CONSTRAINT FK_CAR_ID
FOREIGN KEY(CAR_ID) REFERENCES LOT(CAR_ID);

ALTER TABLE OFFERS
ADD CONSTRAINT UQ_CAR_ID_USER_ID
UNIQUE(CAR_ID,USER_ID);

ALTER TABLE OFFERS
ADD CONSTRAINT FK_USER_ID
FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID);

--get the correct ids from user and car and input them into this one
--EXECUTE ADDOFFER(?,?,?,1000,10);
--EXECUTE ADDOFFER(?,?,?,1500,20); <- do the same car id but different user id
--EXECUTE ADDOFFER(?,?,?,1000,10); <- whatever i want
SELECT * FROM OFFERS;



-------------------------------------------------------------------------------------------------------------------------------
--OWNED CARS TABLE
CREATE TABLE OWNED_CARS(
USER_ID INTEGER,
MAKE VARCHAR2(50),
MODEL VARCHAR2(50),
COLOR VARCHAR2(50),
MILEAGE NUMBER,
PAYMENTS_LEFT NUMBER,
MINIMUM_PAYMENT NUMBER);

-- FUNCTION FOR DETERMINING HOW MUCH PAY IS LEFT
CREATE OR REPLACE FUNCTION GETDOWN (CARID IN INTEGER, USERID IN INTEGER)
        RETURN NUMBER AS
        DOWNPAYMENT NUMBER;
        BEGIN
        SELECT OFFER INTO DOWNPAYMENT
        FROM OFFERS
        WHERE CAR_ID = CARID
             AND USER_ID = USERID;
        RETURN DOWNPAYMENT;
        END;
        /
     CREATE OR REPLACE FUNCTION GETTIME (CARID IN INTEGER, USERID IN INTEGER)
        RETURN NUMBER AS
        PAYTIME INTEGER;
        BEGIN
        SELECT PAY_TIME INTO PAYTIME
        FROM OFFERS
        WHERE CAR_ID = CARID
             AND USER_ID = USERID;
        RETURN PAYTIME;
        END;
        /   
CREATE OR REPLACE FUNCTION CALC_PAYMENTS (CARID IN INTEGER, USERID IN INTEGER)
RETURN NUMBER AS
PAYMENT_OWED NUMBER;
BEGIN
SELECT PRICE INTO PAYMENT_OWED
FROM OFFERS
WHERE CAR_ID = CARID
    AND USER_ID = USERID;
RETURN ((PAYMENT_OWED - GETDOWN(CARID,USERID))/GETTIME(CARID,USERID));
END;
/

--PROCEDURE TO UPDATE ACCEPTED OFFERS INTO OWNED CARS TABLE
CREATE OR REPLACE PROCEDURE UPDATECARINFO(CARID IN INTEGER, USERID IN INTEGER)
AS
BEGIN
INSERT INTO OWNED_CARS
VALUES(USERID,
(SELECT MAKE FROM LOT WHERE CAR_ID = CARID),
(SELECT MODEL FROM LOT WHERE CAR_ID = CARID),
(SELECT COLOR FROM LOT WHERE CAR_ID = CARID),
(SELECT MILEAGE FROM LOT WHERE CAR_ID = CARID),
(SELECT PAY_TIME FROM OFFERS WHERE USER_ID = USERID AND CAR_ID = CARID),
CALC_PAYMENTS(CARID,USERID));

DELETE FROM OFFERS WHERE
CAR_ID = CARID;

DELETE FROM LOT WHERE
CAR_ID = CARID;

COMMIT;
END;
/
-- CONSTRAINTS ON OWNED_CARS
ALTER TABLE OWNED_CARS
ADD CONSTRAINT FK_USER_NAME
FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID);

