-- Create the database
CREATE DATABASE GrammarCheck4;
GO

-- Use the created database
USE GrammarCheck4;
GO

-- Create Users table
CREATE TABLE Users (
    userID INT PRIMARY KEY,
    email NVARCHAR(50),
    customerName NVARCHAR(30),
    phone NVARCHAR(20) UNIQUE,
    [address] NVARCHAR(100),
    age INT
);
GO

-- Create Account table with a primary key on userID
CREATE TABLE Account (
    userID INT PRIMARY KEY,
    phone NVARCHAR(20) UNIQUE,
    accountName NVARCHAR(50),
    [password] NVARCHAR(100), 
    [role] BIT,
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
GO

-- Create Administrator table
CREATE TABLE Administrator (
    adminID INT PRIMARY KEY,
    adminName NVARCHAR(30),
    age INT,
    email NVARCHAR(50),
    phone NVARCHAR(20) UNIQUE,
    FOREIGN KEY (adminID) REFERENCES Account(userID)
);
GO

-- Create UserPost table
CREATE TABLE UserPost (
    postID INT IDENTITY(1,1) PRIMARY KEY,
    userID INT,
    postText NVARCHAR(MAX),
    datePosted DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
GO

-- Create Emotion table
CREATE TABLE Emotion (
    emotionID INT IDENTITY(1,1) PRIMARY KEY,
    userID INT,
    postID INT,
    emotionType NVARCHAR(10), -- 'like' or 'dislike'
    dateEmoted DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (postID) REFERENCES UserPost(postID)
);
GO

-- Alter UserPost table to add like and dislike counters
ALTER TABLE UserPost
ADD likeCount INT DEFAULT 0,
    dislikeCount INT DEFAULT 0;
GO

-- Create Comment table
CREATE TABLE Comment (
    commentID INT PRIMARY KEY,
    userID INT,
    postID INT,
    commentText NVARCHAR(MAX),
    dateCommented DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (postID) REFERENCES UserPost(postID)
);
GO

-- Create Manager table
CREATE TABLE Manager (
    managerID INT PRIMARY KEY,
    email NVARCHAR(50),
    managerName NVARCHAR(30),
    phone NVARCHAR(20) UNIQUE,
    accountID INT,
    FOREIGN KEY (accountID) REFERENCES Account(userID)
);
GO

-- Populate tables with sample data
INSERT INTO Users (userID, email, customerName, phone, [address], age) 
VALUES 
(1, 'user1@example.com', 'Customer 1', '123456789', N'Address 1', 25),
(2, 'user2@example.com', 'Customer 2', '987654321', N'Address 2', 30),
(3, 'user3@example.com', 'Customer 3', '111222333', N'Address 3', 35),
(4, 'user4@example.com', 'Customer 4', '444555666', N'Address 4', 40),
(5, 'user5@example.com', 'Customer 5', '777888999', N'Address 5', 45);

INSERT INTO Account (userID, phone, accountName, [password], [role]) 
VALUES 
(1, '123456789', 'Account 1', 'hashed_password1', 0),
(2, '987654321', 'Account 2', 'hashed_password2', 0),
(3, '111222333', 'Account 3', 'hashed_password3', 0),
(4, '444555666', 'Account 4', 'hashed_password4', 0),
(5, '777888999', 'Account 5', 'hashed_password5', 0);

INSERT INTO Administrator (adminID, adminName, age, email, phone) 
VALUES 
(1, N'Admin 1', 30, 'admin1@example.com', '111111111'),
(2, N'Admin 2', 35, 'admin2@example.com', '222222222'),
(3, N'Admin 3', 40, 'admin3@example.com', '333333333'),
(4, N'Admin 4', 45, 'admin4@example.com', '444444444'),
(5, N'Admin 5', 50, 'admin5@example.com', '555555555');

INSERT INTO UserPost ( userID, postText) 
VALUES 
( 1, N'This is the first post'),
( 2, N'This is the second post'),
( 3, N'This is the third post'),
( 4, N'This is the fourth post'),
( 5, N'This is the fifth post');

INSERT INTO Comment (commentID, userID, postID, commentText) 
VALUES 
(1, 1, 1, N'This is the first comment'),
(2, 2, 2, N'This is the second comment'),
(3, 3, 3, N'This is the third comment'),
(4, 4, 4, N'This is the fourth comment'),
(5, 5, 5, N'This is the fifth comment');

INSERT INTO Manager (managerID, email, managerName, phone, accountID) 
VALUES 
(1, 'manager1@example.com', N'Manager 1', '666666666', 1),
(2, 'manager2@example.com', N'Manager 2', '777777777', 2),
(3, 'manager3@example.com', N'Manager 3', '888888888', 3),
(4, 'manager4@example.com', N'Manager 4', '999999999', 4),
(5, 'manager5@example.com', N'Manager 5', '1010101010', 5);


-- Query to get the post details with like and dislike counts
SELECT userID
FROM Emotion
WHERE postID = 1 AND emotionType = 'like';


-- Query to get the post details with like and dislike counts and userIDs

SELECT p.postID, 
		p.userID,
       p.postText, 
	   p.datePosted,
       ISNULL(likeCountTable.likeCount, 0) AS likeCount, 
       ISNULL(dislikeCountTable.dislikeCount, 0) AS dislikeCount,
       ISNULL(likeUsers.userIDs, '') AS likeUserIDs,
       ISNULL(dislikeUsers.userIDs, '') AS dislikeUserIDs
FROM UserPost p
LEFT JOIN (
    SELECT postID, COUNT(*) AS likeCount
    FROM Emotion
    WHERE emotionType = 'like'
    GROUP BY postID
) AS likeCountTable ON p.postID = likeCountTable.postID
LEFT JOIN (
    SELECT postID, COUNT(*) AS dislikeCount
    FROM Emotion
    WHERE emotionType = 'dislike'
    GROUP BY postID
) AS dislikeCountTable ON p.postID = dislikeCountTable.postID
LEFT JOIN (
    SELECT postID, STRING_AGG(userID, ', ') AS userIDs
    FROM Emotion
    WHERE emotionType = 'like'
    GROUP BY postID
) AS likeUsers ON p.postID = likeUsers.postID
LEFT JOIN (
    SELECT postID, STRING_AGG(userID, ', ') AS userIDs
    FROM Emotion
    WHERE emotionType = 'dislike'
    GROUP BY postID
) AS dislikeUsers ON p.postID = dislikeUsers.postID;

INSERT INTO UserPost (userID, postText) VALUES (3, 'This is the test post')