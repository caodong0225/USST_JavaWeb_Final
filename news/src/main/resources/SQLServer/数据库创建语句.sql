CREATE TABLE Users (
	username NVARCHAR(20) NOT NULL Primary Key,
	password NVARCHAR(20) NOT NULL
);
CREATE TABLE UserTags (
	username NVARCHAR(20) NOT NULL,
	tag NVARCHAR(10) NOT NULL,
	clickCount int,  
	PRIMARY KEY (Username, Tag),
	FOREIGN Key (username) REFERENCES Users(username)
);