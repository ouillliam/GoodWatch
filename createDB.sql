drop table if exists user;
drop table if exists director;
drop table if exists gendra;
drop table if exists movie;
drop table if exists comment;
drop table if exists userMovie;
drop table if exists rating;
drop table if exists gendramovie;

CREATE TABLE `user`(
    userID INT PRIMARY KEY AUTO_INCREMENT,
    `login` VARCHAR(20),
    `password` VARCHAR(20),
    `role` VARCHAR(10),
    CONSTRAINT CHK_USER_ROLE CHECK( `role` IN ("user", "admin"))
);

CREATE TABLE director(
    directorID INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(80)
);

CREATE TABLE genre(
    genreID INT PRIMARY KEY AUTO_INCREMENT,
    genre VARCHAR(30)
);

CREATE TABLE movie(
    movieID INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(80),
    releaseYear INT,
    plot VARCHAR(2000),
    poster VARCHAR(150),
    runtime INT,
    directorID INT,
    FOREIGN KEY (directorID) REFERENCES director(directorID) 
);

CREATE TABLE genreMovie(
    gendraID INT,
    movieID INT,
    PRIMARY KEY (genreID, movieID),
    FOREIGN KEY (genreID) REFERENCES genre(genreID),
    FOREIGN KEY (movieID) REFERENCES movieID(movieID)
);

CREATE TABLE userMovie(
    userID INT,
    movieID INT,
    PRIMARY KEY (userID, movieID),
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (movieID) REFERENCES movie(movieID)
);

CREATE TABLE comment(
    commentID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    movieID INT,
    content VARCHAR(500),
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (movieID) REFERENCES movie(movieID)
);

CREATE TABLE rating(
    userID INT,
    movieID INT,
    rating INT,
    PRIMARY KEY (userID, movieID),
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (movieID) REFERENCES movie(movieID),
    CONSTRAINT CHK_RATING_RANGE CHECK (rating BETWEEN 0 and 5)
);