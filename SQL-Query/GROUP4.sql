-- Create the new merged database
CREATE DATABASE GrammarCheck;
GO

-- Use the created database
USE GrammarCheck;
GO

-- Create Users table
CREATE TABLE Users (
    userID INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    [password] VARCHAR(255),
    phone VARCHAR(12) UNIQUE,
    [address] NVARCHAR(100),
    age INT,
    created_at DATETIME DEFAULT GETDATE(),
    premiumID BIT DEFAULT 0,
	role BIT DEFAULT 0
	)
GO

-- Create SubscriptionPlans table
CREATE TABLE SubscriptionPlans (
    planID INT PRIMARY KEY,
    plan_name VARCHAR(50),
    plan_price DECIMAL(10,2),
    duration_days INT,
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Create Payments table
CREATE TABLE Payments (
    paymentID INT PRIMARY KEY IDENTITY(1,1),
    userID INT,
    planID INT,
    amount DECIMAL(10,2),
    payment_date DATETIME DEFAULT GETDATE(),
    expiry_date DATETIME,
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (planID) REFERENCES SubscriptionPlans(planID)
);
GO

-- Create Administrator table
CREATE TABLE Administrator (
    adminID INT PRIMARY KEY IDENTITY(1,1),
    adminName NVARCHAR(30),
    age INT,
    email NVARCHAR(50),
    phone NVARCHAR(20) UNIQUE,
    userID INT UNIQUE,
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
GO

-- Create UserPost table
CREATE TABLE UserPost (
    postID INT IDENTITY(1,1) PRIMARY KEY,
    userID INT,
    postText NVARCHAR(MAX),
    datePosted DATETIME DEFAULT GETDATE(),
    likeCount INT DEFAULT 0,
    dislikeCount INT DEFAULT 0,
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
GO

-- Create Emotion table
CREATE TABLE Emotion (
    emotionID INT IDENTITY(1,1) PRIMARY KEY,
    userID INT,
    postID INT,
    emotionType NVARCHAR(10), 
    dateEmoted DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (postID) REFERENCES UserPost(postID)
);
GO

-- Create Comment table
CREATE TABLE Comment (
    commentID INT PRIMARY KEY IDENTITY(1,1),
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
    managerID INT PRIMARY KEY IDENTITY(1,1),
    email NVARCHAR(50),
    managerName NVARCHAR(30),
    phone NVARCHAR(20) UNIQUE,
    userID INT UNIQUE,
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
GO

-- Populate Users table
INSERT INTO Users (username, email, [password], phone, [address], age) 
VALUES 
( 'user1', 'user1@example.com', 'hashed_password1', '1234567890', N'Address 1', 25),
( 'user2', 'user2@example.com', 'hashed_password2', '9876543210', N'Address 2', 30),
( 'user3', 'user3@example.com', 'hashed_password3', '1112223334', N'Address 3', 35),
( 'user4', 'user4@example.com', 'hashed_password4', '4445556667', N'Address 4', 40),
( 'user5', 'user5@example.com', 'hashed_password5', '7778889990', N'Address 5', 45);
GO

-- Populate SubscriptionPlans table
INSERT INTO SubscriptionPlans (planID, plan_name, plan_price, duration_days)
VALUES 
(1, 'Basic', 9.99, 30),
(2, 'Premium', 19.99, 30);
GO

-- Populate Payments table
INSERT INTO Payments (userID, planID, amount, expiry_date)
VALUES 
(1, 1, 9.99, DATEADD(DAY, 30, GETDATE())),
(2, 2, 19.99, DATEADD(DAY, 30, GETDATE())),
(3, 1, 9.99, DATEADD(DAY, 30, GETDATE())),
(4, 2, 19.99, DATEADD(DAY, 30, GETDATE())),
(5, 1, 9.99, DATEADD(DAY, 30, GETDATE()));
GO

-- Populate Administrator table
INSERT INTO Administrator (adminName, age, email, phone, userID) 
VALUES 
(N'Admin 1', 30, 'admin1@example.com', '1111111110', 1),
(N'Admin 2', 35, 'admin2@example.com', '2222222220', 2),
(N'Admin 3', 40, 'admin3@example.com', '3333333330', 3),
(N'Admin 4', 45, 'admin4@example.com', '4444444440', 4),
(N'Admin 5', 50, 'admin5@example.com', '5555555550', 5);
GO

-- Populate UserPost table
INSERT INTO UserPost (userID, postText) 
VALUES 
(1, N'This is the first post'),
(2, N'This is the second post'),
(3, N'This is the third post'),
(4, N'This is the fourth post'),
(5, N'This is the fifth post');
GO

-- Populate Comment table
INSERT INTO Comment (userID, postID, commentText) 
VALUES 
(1, 1, N'This is the first comment'),
(2, 2, N'This is the second comment'),
(3, 3, N'This is the third comment'),
(4, 4, N'This is the fourth comment'),
(5, 5, N'This is the fifth comment');
GO

-- Populate Emotion table
INSERT INTO Emotion (userID, postID, emotionType) 
VALUES 
(1, 1, 'like'),
(2, 1, 'dislike'),
(3, 2, 'like'),
(4, 2, 'like'),
(5, 3, 'dislike'),
(1, 3, 'like'),
(2, 4, 'dislike'),
(3, 4, 'dislike'),
(4, 5, 'like'),
(5, 5, 'like');
GO

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
GO


CREATE TABLE userFeedback (
    feedbackID INT PRIMARY KEY IDENTITY(1,1),
    userID INT,
	feedbackTopic NVARCHAR(MAX),
    feedbackText NVARCHAR(MAX),
    FOREIGN KEY (userID) REFERENCES Users(userID),
);
Go


select * from Users
INSERT INTO userFeedback(userID,feedbackTopic,feedbackText)
VALUES 
(1, 'Convenient','"This spell-check software is very convenient and easy to use. Ive significantly reduced errors in my documents."'),
(2, 'Accurate','"I find this software quite accurate in detecting spelling mistakes"'),
(3, 'Feature','"Very satisfied with the word suggestion feature. It helps me write faster and more efficiently."'),
(4, 'More feature','"The interface is user-friendly and easy to navigate. However, more language options should be added."'),
(5, 'Saving time','"This spell-check software saves me a lot of editing time. Highly recommended."'),
(4, 'Missed error','"Sometimes the software still misses a few minor spelling errors. Hopefully, it will be improved in future versions."');

GO


-- Create table
CREATE TABLE CheckHistory (
    checkID INT IDENTITY(1,1) PRIMARY KEY,
    userID INT,
    text NVARCHAR(MAX),
    result NVARCHAR(MAX),
    checkDate DATETIME DEFAULT GETDATE(),
    type BIT, -- 0 for spelling check, 1 for grammar check
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
GO

select * from CheckHistory

-- Insert sample data into CheckHistory
INSERT INTO CheckHistory (userID, text, result, checkDate, type)
VALUES 
(1, N'This is a sample text for grammar check.', N'Grammar check passed.', GETDATE(), 1),
(2, N'This is an example text with a typo.', N'Typo found: "typo".', GETDATE(), 0),
(1, N'Another text for grammar checking.', N'Grammar check passed.', GETDATE(), 1),
(3, N'This text contains a spelng error.', N'Typo found: "spelng".', GETDATE(), 0),
(2, N'This sentence will be checked for grammar.', N'Grammar check passed.', GETDATE(), 1);
GO
