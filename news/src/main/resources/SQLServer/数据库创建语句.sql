CREATE TABLE Users (
	Id INT IDENTITY(1,1) PRIMARY KEY,
	username NVARCHAR(20) NOT NULL,
	password NVARCHAR(20) NOT NULL
);
CREATE TABLE UserTags (
	userId int NOT NULL,
	tag NVARCHAR(10) NOT NULL,
	clickCount int,  
	PRIMARY KEY (userId, Tag),
	Foreign Key (userId) references Users(id)
);