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
	[edited] BIT NOT NULL DEFAULT 0,
    [category] NVARCHAR(100) NULL,
    [status] BIT NOT NULL DEFAULT 0,
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
    feedbackTopic NVARCHAR(100), 
    feedbackText NVARCHAR(MAX),
    created_at DATETIME DEFAULT GETDATE(),
	rating INT DEFAULT 1 ,
	status BIT DEFAULT 0,
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
INSERT INTO Feedback (userID, feedbackTopic, feedbackText,rating,status,role)
VALUES 
(1, 'Convenient', 'This spell-check software is very convenient and easy to use. I have significantly reduced errors in my documents.',5,0,0),
(2, 'Accurate', 'I find this software quite accurate in detecting spelling mistakes',3,0,0),
(3, 'Feature', 'Very satisfied with the word suggestion feature. It helps me write faster is user-friendly and easy to navigate. However, more language options should be added.',2,0,0),
(5, 'Saving time', 'This spell-check software saves me a lot of editing time. Highly recommended.',1,0,0),
(3, 'Missed error', 'Sometimes the software still misses a few minor spelling errors. Hopefully, it will be improved in future versions.',1,0,0);
GO




-- Create table HelpCenter
CREATE TABLE HelpCenter (
    heID INT PRIMARY KEY,
	questionContent NVARCHAR(255),
	answerContent NVARCHAR(255),
	topicID INT,
	FOREIGN KEY (topicId) references TopicHelpCenter(topicID)
);
CREATE TABLE TopicHelpCenter (
    topicID INT PRIMARY KEY,
	topicName NVARCHAR(255),
	topicDetail NVARCHAR(255),
	topicPics NVARCHAR(255)
);
insert into TopicHelpCenter(topicID,topicName, topicDetail, topicPics)
values
(1, 'Account','Manage your account settings and learn about username changes and other account-related topics', 'images/accoumt.png' ),
(2, 'Grammar Checking','Find out how to use the grammar checker, report issues, and customize settings for better text accuracy.','images/grammar.png'),
(3, 'Post','Learn how to create, edit, publish, and manage your blog posts effectively.','images/post.png'),
(4, 'Contact us','Get in touch with our support team for any assistance or inquiries you may have.','images/contact.png')

INSERT INTO HelpCenter (heID, questionContent, answerContent, topicID)
VALUES 
(1, 'How do I create an account?', 'To create an account, click the register button at the top right and fill in the necessary information.', 1),
(2, 'How do I reset my password?', 'Click on the "Forgot Password" link on the login page and follow the instructions.', 1),
(3, 'How do I update my profile information?', 'Go to your account settings and click on "Edit Profile". Make the necessary changes and save.', 1),
(4, 'How do I delete my account?', 'Please contact our support team at support@example.com to request account deletion.', 1),
(5, 'What should I do if I don’t receive the account verification email?', 'Check your spam folder and ensure you entered the correct email address. If the issue persists, contact support.', 1),
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



CREATE TABLE PostReport (
	reportID int primary key IDENTITY(1,1),
    postID INT,
	userID INT,
	userReportID INT,
	reportType NVARCHAR(255),
	[message] NVARCHAR(255),
	created_at DATETIME DEFAULT GETDATE(),

	FOREIGN KEY (postID) references UserPost(postID),
	Foreign key (userID) references Users(userID)
);
select * from UserPost

CREATE TABLE Advertise (
    adID INT PRIMARY KEY IDENTITY(1,1), -- Mã quảng cáo tự động tăng
    title NVARCHAR(255) NOT NULL, -- Tiêu đề quảng cáo
    description NVARCHAR(MAX) NOT NULL, -- Mô tả quảng cáo
    imageAd NVARCHAR(255), -- Đường dẫn URL hình ảnh quảng cáo
    isActive BIT NOT NULL,  -- Trạng thái hoạt động của quảng cáo (0: không hoạt động, 1: hoạt động)
    userID int, -- Người tạo quảng cáo
	created_at DATETIME DEFAULT GETDATE(),
	FOREIGN KEY (userID) references Users(userID)
);
CREATE TABLE AdvertiseEmails (
    emailAdID INT PRIMARY KEY IDENTITY(1,1), -- Mã email tự động tăng
    adID INT NOT NULL, -- Mã quảng cáo, là khóa ngoại
    userID int NOT NULL, -- Email người nhận quảng cáo
    sendDate DATETIME DEFAULT GETDATE(), -- Ngày gửi quảng cáo
    FOREIGN KEY (adID) REFERENCES advertise(adID), -- Thiết lập khóa ngoại
    FOREIGN KEY (userID) REFERENCES Users(userID) -- Thiết lập khóa ngoại

);
select a.adID, a.title, a.description,a.imageAd, a.isActive,
a.userID as adminID , ae.userID, ae.sendDate as ReceivedID
FROM Advertise a join  AdvertiseEmails ae
ON a.adID = ae.adID

select * from Advertise
where isActive= 1

select a.adID,a.title,a.description,a.imageAd,a.isActive, u.username as adminName, a.created_at
from Advertise a join Users u ON a.userID=u.role
ORDER BY a.adID DESC

	INSERT INTO Advertise (title, description, imageAd, isActive, userID) VALUES
	(N'Quảng cáo 1', N'Mô tả chi tiết về quảng cáo 1', 'images/post.png', 1, 1),
	(N'Quảng cáo 2', N'Mô tả chi tiết về quảng cáo 2', 'images/img_1.png', 0, 1),
	(N'Quảng cáo 3', N'Mô tả chi tiết về quảng cáo 3', 'images/img.jpg', 1, 1),
	(N'Quảng cáo 4', N'Mô tả chi tiết về quảng cáo 4', 'images/contact.png', 1, 1),
	(N'Quảng cáo 5', N'Mô tả chi tiết về quảng cáo 5', 'images/accoumt.png', 0, 1),
	(N'Nguyên xấu trai ngu vl đần đụt điên', N'k có gì để nói', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fdantri.com.vn%2Fkhoa-hoc-cong-nghe%2Flam-the-nao-de-tinh-tuoi-con-meo-cua-ban-20230124073204167.htm&psig=AOvVaw3SH11BpuTH_A0hsjZFKHr4&ust=1721448362594000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCID8hIKdsocDFQAAAAAdAAAAABAI', 0, 1);

(N'Đạt của Bình', N'Chắc là thế', 'http://example.com/image5.jpg', 0, 1);

Select * from Advertise a
where a.isActive = 0

select a.adID,a.title,a.description,a.imageAd,a.isActive, u.username as adminName, a.created_at
from Advertise a join Users u ON a.userID=u.role
where a.title LIKE '%5%'

select * from advertise

CREATE TABLE ContactUs (
    contactID INT IDENTITY(1,1) PRIMARY KEY,
    UserName NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255) NOT NULL,
    Subject NVARCHAR(255) NOT NULL,
    Message NVARCHAR(MAX) NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE(),
	status BIT
); 
