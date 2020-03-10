-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
	("ticketofficer1", "password", "Juan", "Gabin", "juan.gabin@udc.es", 0);
	
INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
	("ticketofficer2", "password", "Elena", "Nito", "elena@udc.es", 0);

INSERT INTO City (name) VALUES ("A Corunha");
INSERT INTO City (name) VALUES ("SDC");
INSERT INTO City (name) VALUES ("Ponferrada");
	
INSERT INTO Cinema (name, cityId) VALUES ("Yelmo", 1);
INSERT INTO Cinema (name, cityId) VALUES ("CINESA", 1);
INSERT INTO Cinema (name, cityId) VALUES ("Cantones Cines", 1);
INSERT INTO Cinema (name, cityId) VALUES ("Cines Compostela", 2);
INSERT INTO Cinema (name, cityId) VALUES ("NUMAX", 2);
INSERT INTO Cinema (name, cityId) VALUES ("La Dehesa", 3);

INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 100, 1);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 50, 1);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 150, 2);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 50, 2);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 100, 3);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 80, 3);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 65, 4);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 75, 5);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 80, 5);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 1", 100, 6);
INSERT INTO Room (name, capacity, cinemaId) VALUES ("Sala 2", 120, 6);

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
her, she must somehow prove she’s being stalked by a vicious, invisible\
man.", 140);  

INSERT INTO Session (movieId, roomId, date, price) VALUE (1, 1, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (2, 2, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (3, 3, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (1, 4, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (1, 5, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (2, 6, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (3, 7, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (1, 8, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (4, 9, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (2, 10, DATE '2021-06-18', 5.99);
INSERT INTO Session (movieId, roomId, date, price) VALUE (1, 11, DATE '2021-06-18', 5.99);
