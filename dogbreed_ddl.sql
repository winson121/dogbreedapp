DROP DATABASE IF EXISTS dogbreed;

CREATE DATABASE dogbreed;

DROP TABLE IF EXISTS dogbreed.breed;
CREATE TABLE dogbreed.breed(
	ID int NOT NULL,
    breed_name varchar(30),
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS dogbreed.subbreed;
CREATE TABLE dogbreed.subbreed(
	ID int NOT NULL,
    subbreed_name varchar(30),
    breed_id int,
    PRIMARY KEY (ID),
    FOREIGN KEY (breed_id) REFERENCES Breed(ID)
);

DROP TABLE IF EXISTS dogbreed.image;
CREATE TABLE dogbreed.image(
	ID int not null,
    image_link varchar(80),
    subbreed_id int,
    PRIMARY KEY (ID),
    FOREIGN KEY (subbreed_id) REFERENCES Subbreed(ID)
);
