/**
drop table address;
drop table cabin;
drop table cabinreservation;
drop table creditcard;
drop table cruise;
drop table customer;
drop table phone;
drop table reservation;
drop table reservationcustomer;
drop table ship;
**/


create table Cabin
(
    id int primary key NOT NULL,
    shipId int,
    bedCount int,
    name char(30),
    deckLevel int
);

create table Address (
	id int primary key,
	street varchar(255),
	city varchar(255),
	state varchar(255),
	zip varchar(5)
);

CREATE TABLE CreditCard
(
    id INT PRIMARY KEY NOT NULL,
    expirationDate DATE,
    number CHAR(20),
    name CHAR(40),
    organization CHAR(20),
);

create table Customer (
   id long primary key not null,
   firstName VARCHAR(255),
   lastName VARCHAR(255),
   hasGoodCredit int,
   addressId int,
   creditCardId int,
	FOREIGN KEY(addressId) REFERENCES Address(id),
	FOREIGN KEY(creditCardId) REFERENCES CreditCard(id)
);


CREATE TABLE Phone
(
    id INT PRIMARY KEY NOT NULL,
    number CHAR(20),
    type INT,
    customerId INT,
	foreign key (customerId) references Customer(id)
);


CREATE TABLE Ship
(
    id INT PRIMARY KEY NOT NULL,
    name CHAR(30),
    tonnage DECIMAL (8,2)
);


CREATE TABLE Cruise
(
    id INT PRIMARY KEY NOT NULL,
    name CHAR(30),
    shipId INT,
	foreign key (shipId) references Ship(id)
);


CREATE TABLE Reservation
(
    id INT PRIMARY KEY NOT NULL,
    amountPaid DECIMAL (8,2),
    dateReserved DATE,
    cruiseId INT,
	foreign key (cruiseId) references Cruise(id)
);


CREATE TABLE ReservationCustomer
(
    reservationId INT,
    customerId INT,
	FOREIGN KEY (reservationId) REFERENCES Reservation(id),
	FOREIGN KEY (customerId) REFERENCES Customer(id)
);


CREATE TABLE CabinReservation
(
    reservationId INT,
    cabinId INT,
	FOREIGN KEY (reservationId) REFERENCES Reservation(id),
	FOREIGN KEY (cabinId) REFERENCES Cabin(id)
);

create table GENERATOR_TABLE
(
    PRIMARY_KEY_COLUMN VARCHAR not null,
    VALUE_COLUMN long not null
);


CREATE TABLE PAYMENT
(
    customer_id     INTEGER,
    amount          DECIMAL(8,2),
    type            CHAR(10),
    check_bar_code  CHAR(50),
    check_number    INTEGER,
    credit_number   CHAR(20),
    credit_exp_date DATE
)