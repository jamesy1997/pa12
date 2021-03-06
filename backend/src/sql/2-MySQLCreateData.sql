-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
	("viewer", "$2a$10$Sz098HdIbrj2Qr3C2ojNKOYuG46VxdUz3dQSAbFOhMMyo6S2JSCQK", "viewer", "tester", "viewer.tester@udc.es", 1);
	
INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
	("ticketseller", "$2a$10$o4TYmbQ9Uvk3AO9tvRvFgO0OEYXz8Y5bEiCVYpuiTC/CwfBNpVewS", "ticketseller", "tester", "ticketseller.tester@udc.es", 0);
	
INSERT INTO City (name) VALUES ("A Corunha");
INSERT INTO City (name) VALUES ("SDC");
INSERT INTO City (name) VALUES ("Ponferrada");
	
INSERT INTO Cinema (name, cityId) VALUES ("Yelmo", 1);
INSERT INTO Cinema (name, cityId) VALUES ("CINESA", 1);
INSERT INTO Cinema (name, cityId) VALUES ("NUMAX", 2);
INSERT INTO Cinema (name, cityId) VALUES ("Cines Compostela", 2);
INSERT INTO Cinema (name, cityId) VALUES ("CinesP", 3);
INSERT INTO Cinema (name, cityId) VALUES ("La Dehesa", 3);

INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 100, 1);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 25, 1);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 150, 2);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 50, 2);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 100, 3);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 80, 3);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 65, 4);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 75, 5);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 80, 5);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 8, 6);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 9, 6);

INSERT INTO Movie (title, summary, duration) VALUES ("1917", "Amid the\
anarchic chaos of the First World War, two young British soldiers are\
tasked with carrying a critical warning message across enemy territory\
in a bid to save the lives of 1,600 men. It seems like an impossible\
mission and time is running out.", 120);

INSERT INTO Movie (title, summary, duration) VALUES ("Knives Out", "When a\
family gathering goes horribly wrong, three detectives are sent to\
unravel the puzzling mystery behind the death of one of the guests", 130);
 
INSERT INTO Movie (title, summary, duration) VALUES ("Bombshell", "When\
Gretchen Carlson makes a stand against the inappropriate behaviour and\
sexual assault prevalent at Fox News, she inspires other victims to\
reveal their truths. The women fight to show that those in power\
should be held accountable.", 154); 

INSERT INTO Movie (title, summary, duration) VALUES ("The invisible man",
"After hearing her abusive ex has died and left her millions in his will,\
Cecilia suspect his death was fake. With lethal events occurring around\
her, she must somehow prove she???s being stalked by a vicious, invisible\
man.", 140);  

INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE), 5.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE), 5.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (3, 3, DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE), 4.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (1, 4, DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE), 3.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (1, 5, DATE_ADD(DATE(NOW()), INTERVAL '0 17:55' DAY_MINUTE), 5.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (2, 6, DATE_ADD(DATE(NOW()), INTERVAL '1 15:05' DAY_MINUTE), 4.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (3, 7, DATE_ADD(DATE(NOW()), INTERVAL '2 20::05' DAY_MINUTE), 5.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (1, 8, DATE_ADD(DATE(NOW()), INTERVAL '3 10:05' DAY_MINUTE), 3.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (4, 9, DATE_ADD(DATE(NOW()), INTERVAL '4 18:05' DAY_MINUTE), 5.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (2, 10, DATE_ADD(DATE(NOW()), INTERVAL '5 19:05' DAY_MINUTE), 5.99, ticketsPurchased, version);
INSERT INTO Session (movieId, roomId, date, price, ticketsPurchased, version) VALUE (1, 11, DATE_ADD(DATE(NOW()), INTERVAL '5 20:05' DAY_MINUTE), 5.99, ticketsPurchased, version);

INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (6, 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 10:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (7, 3, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 15:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (8, 4, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 20:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (9, 5, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 10:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (6, 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 10:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (10, 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 12:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (6, 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 13:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (6, 2, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 10:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (7, 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 14:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (6, 10, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 10:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (8, 5, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 18:00' DAY_MINUTE), 0, 123456789);
INSERT INTO Purchase(sessionId, ticket, userId, date, pickedUp, creditCard) VALUE (6, 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 19:00' DAY_MINUTE), 0, 123456789);
