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
    phone VARCHAR(20) UNIQUE,  -- Changed phone length to 20
    [address] NVARCHAR(100),
    age INT,
    created_at DATETIME DEFAULT GETDATE(),
    premiumID BIT DEFAULT 0,
    role BIT DEFAULT 0
);
GO

-- Create SubscriptionPlans table
CREATE TABLE SubscriptionPlans (
    planID INT IDENTITY(1,1) PRIMARY KEY,
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


-- Create CheckHistory table
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

-- Create Feedback table
CREATE TABLE Feedback (
    feedbackID INT PRIMARY KEY IDENTITY(1,1),
    userID INT,
    feedbackTopic NVARCHAR(100),  -- Adjusted length to 100
    feedbackText NVARCHAR(MAX),
    created_at DATETIME DEFAULT GETDATE(),
	role BIT DEFAULT 0
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
GO

-- Insert into Users table
INSERT INTO Users (username, email, [password], phone, [address], age, role) 
VALUES 
('user1', 'user1@example.com', 'hashed_password1', '1234567890', N'Address 1', 25, 0),
('user2', 'user2@example.com', 'hashed_password2', '9876543210', N'Address 2', 30, 0),
('user3', 'user3@example.com', 'hashed_password3', '1112223334', N'Address 3', 35, 0),
('user4', 'user4@example.com', 'hashed_password4', '4445556667', N'Address 4', 40, 0),
('user5', 'user5@example.com', 'hashed_password5', '7778889991', N'Address 5', 45, 0), 
('admin1', 'admin1@example.com', '123', '7778889992', N'Address 6', 44, 1);  

GO

-- Insert into SubscriptionPlans and Payments tables
INSERT INTO SubscriptionPlans (plan_name, plan_price, duration_days)
VALUES 
('Basic', 9.99, 30),
('Premium', 19.99, 30);
GO

-- Correct userID values to match existing users
INSERT INTO Payments (userID, planID, amount, expiry_date)
VALUES 
(1, 1, 9.99, DATEADD(DAY, 30, GETDATE())),
(2, 2, 19.99, DATEADD(DAY, 30, GETDATE())),
(3, 1, 9.99, DATEADD(DAY, 30, GETDATE())),
(4, 2, 19.99, DATEADD(DAY, 30, GETDATE())),
(5, 1, 9.99, DATEADD(DAY, 30, GETDATE()));
GO

INSERT INTO UserPost (userID, postText) 
VALUES 
(1, N'This is the first post'),
(2, N'This is the second post'),
(3, N'This is the third post'),
(4, N'This is the fourth post'),
(5, N'This is the fifth post');
GO

-- Continue inserting into other related tables (Comment, Emotion, CheckHistory, Feedback) similarly

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

-- Insert sample data into CheckHistory
INSERT INTO CheckHistory (userID, text, result, checkDate, type)
VALUES 
(1, N'This is a sample text for grammar check.', N'Grammar check passed.', GETDATE(), 1),
(2, N'This is an example text with a typo.', N'Typo found: "typo".', GETDATE(), 0),
(1, N'Another text for grammar checking.', N'Grammar check passed.', GETDATE(), 1),
(3, N'This text contains a spelling error.', N'Typo found: "spelling".', GETDATE(), 0),
(2, N'This sentence will be checked for grammar.', N'Grammar check passed.', GETDATE(), 1);
GO

-- Populate Feedback table
INSERT INTO Feedback (userID, feedbackTopic, feedbackText,role)
VALUES 
(1, 'Convenient', 'This spell-check software is very convenient and easy to use. I have significantly reduced errors in my documents.',0),
(2, 'Accurate', 'I find this software quite accurate in detecting spelling mistakes',0),
(3, 'Feature', 'Very satisfied with the word suggestion feature. It helps me write faster is user-friendly and easy to navigate. However, more language options should be added.',0),
(5, 'Saving time', 'This spell-check software saves me a lot of editing time. Highly recommended.',0),
(3, 'Missed error', 'Sometimes the software still misses a few minor spelling errors. Hopefully, it will be improved in future versions.',0);
GO

-- Query to fetch UserPost details with like and dislike counts and user IDs
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
    SELECT postID, STRING_AGG(CAST(userID AS VARCHAR), ', ') AS userIDs
    FROM Emotion
    WHERE emotionType = 'like'
    GROUP BY postID
) AS likeUsers ON p.postID = likeUsers.postID
LEFT JOIN (
    SELECT postID, STRING_AGG(CAST(userID AS VARCHAR), ', ') AS userIDs
    FROM Emotion
    WHERE emotionType = 'dislike'
    GROUP BY postID
) AS dislikeUsers ON p.postID = dislikeUsers.postID;
GO

-- Create table Topic Help Center
CREATE TABLE TopicHelpCenter (
    topicID INT PRIMARY KEY,
	topicName NVARCHAR(255),

);

-- Create table HelpCenter
CREATE TABLE HelpCenter (
    heID INT PRIMARY KEY,
	questionContent NVARCHAR(255),
	answerContent NVARCHAR(255),
	topicID INT,
	FOREIGN KEY (topicId) references TopicHelpCenter(topicID)
);

insert into TopicHelpCenter(topicID,topicName)
values
(1, 'Account'),
(2, 'Grammar Checking'),
(3, 'Post'),
(4, 'Contact us')

INSERT INTO HelpCenter (heID, questionContent, answerContent, topicID)
VALUES 
(1, 'How do I create an account?', 'To create an account, click the register button at the top right and fill in the necessary information.', 1),
(2, 'How do I reset my password?', 'Click on the "Forgot Password" link on the login page and follow the instructions.', 1),
(3, 'How do I update my profile information?', 'Go to your account settings and click on "Edit Profile". Make the necessary changes and save.', 1),
(4, 'How do I delete my account?', 'Please contact our support team at support@example.com to request account deletion.', 1),
(5, 'What should I do if I don�t receive the account verification email?', 'Check your spam folder and ensure you entered the correct email address. If the issue persists, contact support.', 1),
(6, 'How do I check grammar in my post?', 'To check grammar in your post, click on the "Check Grammar" button before publishing.', 2),
(7, 'What grammar rules does the grammar checker follow?', 'Our grammar checker follows standard English grammar rules and is constantly updated to improve accuracy.', 2),
(8, 'Can I check grammar for languages other than English?', 'Currently, our grammar checker only supports English. We are working on adding support for more languages.', 2),
(9, 'How accurate is the grammar checker?', 'Our grammar checker is highly accurate but not perfect. It provides suggestions that you can review and accept or reject.', 2),
(10, 'How do I post a blog?', 'To post a blog, go to the blog section, click on "New Post", and fill out your content. Then click "Publish".', 3),
(11, 'How do I edit an existing blog post?', 'Go to your blog post, click on "Edit", make the necessary changes, and save.', 3),
(12, 'How do I delete a blog post?', 'Go to your blog post, click on "Delete", and confirm your action.', 3),
(13, 'How can I increase the visibility of my blog post?', 'Ensure your post is well-written and includes relevant keywords. Share your post on social media to reach a wider audience.', 3),
(14, 'What should I do if my blog post is not displaying correctly?', 'Clear your browser cache and refresh the page. If the problem persists, contact support.', 3),
(15, 'How can I contact support?', 'You can contact support via email at support@example.com or by calling 123-456-7890.', 4),
(16, 'What are the support hours?', 'Our support team is available 24/7 to assist you with any issues or questions you may have.', 4),
(17, 'Can I chat with support live?', 'Yes, live chat is available on our website. Click on the chat icon at the bottom right corner of the page.', 4),
(18, 'Where can I find the FAQ section?', 'The FAQ section is available on our website under the Help Center. You can find answers to common questions there.', 4),
(19, 'How do I report a bug or issue?', 'To report a bug, please email support@example.com with a detailed description of the issue.', 4),
(20, 'How do I verify my email address?', 'After registering, you will receive an email with a verification link. Click the link to verify your email address.', 1),
(21, 'What should I do if I forgot my username?', 'Click on the "Forgot Username" link on the login page and follow the instructions.', 1),
(22, 'Can I change my username?', 'Currently, changing the username is not supported. Please contact support for more information.', 1),
(23, 'How do I change my password?', 'Go to your account settings and click on "Change Password". Enter your current password and the new password.', 1),
(24, 'How do I enable two-factor authentication?', 'Go to your account settings and click on "Security". Follow the instructions to enable two-factor authentication.', 1),
(25, 'How do I deactivate my account?', 'To deactivate your account, go to account settings, click on "Deactivate Account", and follow the prompts.', 1),
(26, 'What should I do if my account is locked?', 'If your account is locked, please contact support at support@example.com for assistance.', 1),
(27, 'How do I update my email preferences?', 'Go to your account settings and click on "Email Preferences". Select the types of emails you want to receive.', 1),
(28, 'How do I subscribe to the newsletter?', 'Go to the newsletter section on the website and enter your email address to subscribe.', 1),
(29, 'How do I check my subscription status?', 'Go to your account settings and click on "Subscription". You will see the details of your current subscription.', 1),
(30, 'Does the grammar checker work on mobile devices?', 'Yes, the grammar checker is fully compatible with mobile devices.', 2),
(31, 'Can I use the grammar checker offline?', 'No, the grammar checker requires an internet connection to function.', 2),
(32, 'How do I report a false positive from the grammar checker?', 'You can report false positives by clicking on the feedback link provided in the grammar checker interface.', 2),
(33, 'Are there keyboard shortcuts for the grammar checker?', 'Yes, you can use Ctrl+G to open the grammar checker in the editor.', 2),
(34, 'Can I customize the grammar checker settings?', 'Currently, customization of grammar checker settings is limited. We are working on adding more options.', 2),
(35, 'How do I save a draft of my blog post?', 'When writing a post, click on "Save Draft" to save your progress without publishing.', 3),
(36, 'How do I preview my blog post before publishing?', 'Click on the "Preview" button to see how your post will look once published.', 3),
(37, 'Can I schedule a blog post to be published later?', 'Yes, you can set a future publish date and time when creating or editing your post.', 3),
(38, 'How do I add images to my blog post?', 'Click on the "Insert Image" button in the post editor and upload the image you want to include.', 3),
(39, 'Can I allow comments on my blog post?', 'Yes, you can enable or disable comments in the post settings before publishing.', 3),
(40, 'How do I moderate comments on my blog post?', 'Go to the comments section of your post and you will have options to approve, delete, or reply to comments.', 3),
(41, 'How do I report inappropriate comments?', 'Click on the "Report" button next to the comment you want to report.', 3),
(42, 'Can I edit my comment after posting?', 'No, currently editing comments is not supported. Please delete and repost your comment if necessary.', 3),
(43, 'How do I contact support for billing issues?', 'For billing issues, contact support at billing@example.com.', 4),
(44, 'How do I update my payment information?', 'Go to your account settings and click on "Payment Information". Update your details and save.', 4),
(45, 'What payment methods are accepted?', 'We accept credit cards, debit cards, and PayPal.', 4),
(46, 'Can I get a refund?', 'Refunds are handled on a case-by-case basis. Please contact support for assistance.', 4),
(47, 'How do I view my payment history?', 'Go to your account settings and click on "Payment History" to view your past transactions.', 4),
(48, 'How do I upgrade my subscription plan?', 'Go to your account settings, click on "Subscription", and select the plan you want to upgrade to.', 4),
(49, 'How do I cancel my subscription?', 'Go to your account settings, click on "Subscription", and follow the prompts to cancel your subscription.', 4),
(50, 'What should I do if I am charged incorrectly?', 'Please contact support at billing@example.com if you notice any incorrect charges on your account.', 4),
(51, 'How do I update my contact information?', 'Go to your account settings and click on "Contact Information". Update your details and save.', 4),
(52, 'Where can I find the privacy policy?', 'The privacy policy is available on our website footer. Click on the "Privacy Policy" link to view it.', 4),
(53, 'How do I delete my payment method?', 'Go to your account settings, click on "Payment Information", and remove the payment method you want to delete.', 4);





select*from Feedback
SELECT * FROM Feedback;


SELECT f.feedbackID, f.userID, u.username, f.feedbackTopic, f.feedbackText, f.created_at
FROM Feedback f 
JOIN Users u ON f.userID = u.userID