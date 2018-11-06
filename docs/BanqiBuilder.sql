

 CREATE TABLE UserHistory
 (
	 UserHistoryID MEDIUMINT NOT NULL AUTO_INCREMENT,
	 SerializedObject BLOB NOT NULL,
	 PRIMARY_KEY(UserHistoryID)
 );
 
 
 
 CREATE TABLE UserTable
 (
 	UserID MEDIUMINT NOT NULL AUTO_INCREMENT,
	Email VARCHAR(300) NOT NULL,
	Username VARCHAR(300) NOT NULL,
	SerializedObject BLOB NOT NULL,
	PRIMARY_KEY(UserID)
 );
 
 
 CREATE TABLE Invite
 (
	 InviteID MEDIUMINT NOT NULL AUTO_INCREMENT,
	 SerializedObject BLOB NOT NULL,
	 PRIMARY_KEY(InviteID)
 )
 
 CREATE TABLE GameRecord
 (
 	 GameRecordID MEDIUMINT NOT NULL AUTO_INCREMENT,
	 SerializedObject BLOB NOT NULL,
	 PRIMARY_KEY(GameRecordID)
	
 );
 
 CREATE TABLE Friend
 (
	 UserIDOne MEDIUMINT REFERENCES User(UserID),
	 UserIDTwo MEDIUMINT REFERENCES User(UserID),
	 PRIMARY KEY(UserIDOne, UserIDTwo)
 );
 
 CREATE TABLE Game
 (
	 GameID MEDIUMINT NOT NULL AUTO_INCREMENT,
	 SerializedObject BLOB NOT NULL,
	 PRIMARY_KEY(GameID)
 );
 
 /*CREATE TABLE Board
 (
     BoardID int IDENTITY(001,1) PRIMARY KEY
 );*/
 /*CREATE TABLE UserHistory
 (   
     UserHistoryID int Identity(001,1) PRIMARY KEY,
     Wins int,
     Losses int,
     Draws int,
     GamesPlayed int
 );*/
 /*
 CREATE TABLE UserTable
 (   
     UserID int IDENTITY(001,1) PRIMARY KEY,
     Username VARCHAR(256),
     Email VARCHAR(256),
     Password VARCHAR(256),
     UserHistoryID INTEGER NOT NULL REFERENCES UserHistory(UserHistoryID)
 );*/
 

/*CREATE TABLE Game
   (   
       GameID int IDENTITY(001,1) PRIMARY KEY,
       UserOneID INTEGER NOT NULL REFERENCES UserTable(UserID),
       UserTwoID INTEGER NOT NULL REFERENCES UserTable(UserID),
       UserOneColor VARCHAR(256),
       UserTwoColor VARCHAR(256),
       Turn VARCHAR(256),
       BoardID INTEGER NOT NULL REFERENCES Board(BoardID)
  );*/
 

 
/*CREATE TABLE Invite
 (   
     InviteID int IDENTITY(001,1) PRIMARY KEY,
     FromUserID INTEGER NOT NULL REFERENCES UserTable(UserID),
     ToUserID INTEGER NOT NULL REFERENCES UserTable(UserID),
 );*/


 /*CREATE TABLE UserInvite
 (   
     UserID INTEGER NOT NULL REFERENCES UserTable(UserID),
     InviteID INTEGER NOT NULL REFERENCES Invite(InviteID),
    PRIMARY KEY (UserID, InviteId),
 );*/



 
 
 /*CREATE TABLE GameRecord
 (
     GameRecordID int IDENTITY(001,1) PRIMARY KEY,
     GameID INTEGER NOT NULL REFERENCES Game(GameID),
     WinnerID INTEGER NOT NULL REFERENCES UserTable(UserID),
     LoserID INTEGER NOT NULL REFERENCES UserTable(UserID),
     StartTime SMALLDATETIME,
     EndTime SMALLDATETIME,
     GameOutcome VARCHAR(256)
 );*/

/* 
 CREATE TABLE Piece
 (
     PieceID int IDENTITY(1,1) PRIMARY KEY,
     PType VARCHAR(256),
     XLocation INTEGER,
     YLocation INTEGER,
     BoardID INTEGER NOT NULL REFERENCES Board(BoardID)
 );
 //docker run -e ‘ACCEPT_EULA=Y’ -p 1401:1433 ashellum/banqidatabase:DB
 */
