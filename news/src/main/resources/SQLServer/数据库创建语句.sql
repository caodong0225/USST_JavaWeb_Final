CREATE TABLE Users (
	username NVARCHAR(255) NOT NULL Primary Key,
	password NVARCHAR(255) NOT NULL,
	birthday DATETIME NOT NULL,
	sex NVARCHAR(10),
    career NVARCHAR(255),
    country NVARCHAR(255),
    educationBackground NVARCHAR(255),
);
CREATE TABLE UserTags (
	username NVARCHAR(255) NOT NULL,
	tag NVARCHAR(10) NOT NULL,
	time DATETIME NOT NULL,
	PRIMARY KEY (Username, Tag),
	FOREIGN Key (username) REFERENCES Users(username)
);
CREATE TABLE News (
    id NVARCHAR(255) NOT NULL,
    title NVARCHAR(255) NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
    author NVARCHAR(255) NOT NULL,
    cover NVARCHAR(255),
    date DATE NOT NULL,
	tags NVARCHAR(255),
    zone NVARCHAR(255),
    PRIMARY KEY (id)
);