-- Indexes for primary keys have been explicitly created.

DROP TABLE Purchase;
DROP TABLE Session;
DROP TABLE Room;
DROP TABLE Cinema; 
DROP TABLE City; 
DROP TABLE Movie;  
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Movie (
	id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(60) NOT NULL, 
	summary VARCHAR(1000) NOT NULL,
	duration BIGINT NOT NULL, 
	CONSTRAINT MoviePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE City (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
	CONSTRAINT CityPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Cinema (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
	cityId BIGINT NOT NULL,
	CONSTRAINT CinemaPK PRIMARY KEY (id),
	CONSTRAINT CinemaCityIdFK FOREIGN KEY(cityId)
		REFERENCES City (id)
) ENGINE = InnoDB;

CREATE TABLE Room (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
	capacity BIGINT NOT NULL, 
	cinemaId BIGINT NOT NULL, 
	CONSTRAINT RoomPK PRIMARY KEY (id),
	CONSTRAINT RoomCinemaIdFK FOREIGN KEY(cinemaId)
		REFERENCES Cinema (id)
) ENGINE = InnoDB;

CREATE TABLE Session (
	id BIGINT NOT NULL AUTO_INCREMENT,
	movieId BIGINT NOT NULL, 
	roomId BIGINT NOT NULL, 
	date DATETIME NOT NULL, 
	price DECIMAL(11, 2) NOT NULL,
	remainingTickets INTEGER NOT NULL,
	version BIGINT NOT NULL,
	CONSTRAINT SessionPK PRIMARY KEY (id),
	CONSTRAINT SessionMovieIdFK FOREIGN KEY(movieId)
		REFERENCES Movie (id),
	CONSTRAINT SessionRoomIdFK FOREIGN KEY(roomId)
		REFERENCES Room (id)
) ENGINE = InnoDB;

CREATE TABLE Purchase (
	id BIGINT NOT NULL AUTO_INCREMENT,
	sessionId BIGINT NOT NULL, 
	ticket BIGINT NOT NULL, 
	userId BIGINT NOT NULL, 
	date DATETIME NOT NULL, 
	pickedUp BOOLEAN NOT NULL,
	creditCard BIGINT NOT NULL,
	CONSTRAINT PurchasePK PRIMARY KEY (id),
	CONSTRAINT PurchaseSessionIdFK FOREIGN KEY (sessionId) 
		REFERENCES Session (id),
	CONSTRAINT PurchaseUserIdFK FOREIGN KEY (userId)
		REFERENCES User (id)
) ENGINE = InnoDB;
