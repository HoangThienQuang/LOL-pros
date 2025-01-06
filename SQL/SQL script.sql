-- DROP SCHEMA dbo;

-- CREATE SCHEMA dbo;
-- LoL_Pros_02.dbo.Player definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.Player;

CREATE TABLE LoL_Pros_02.dbo.Player (
	playerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	ingameName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	playerFirstName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	playerLastMiddleName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	dob date NULL,
	nationality varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[role] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT Player_PK PRIMARY KEY (playerId),
	CONSTRAINT Player_UNIQUE UNIQUE (ingameName)
);
ALTER TABLE LoL_Pros_02.dbo.Player WITH NOCHECK ADD CONSTRAINT Player_CHECK CHECK (([role]='SP' OR [role]='AD' OR [role]='MID' OR [role]='JUNG' OR [role]='TOP'));


-- LoL_Pros_02.dbo.Region definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.Region;

CREATE TABLE LoL_Pros_02.dbo.Region (
	regionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	description varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Region_PK PRIMARY KEY (regionName)
);


-- LoL_Pros_02.dbo.Tournament definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.Tournament;

CREATE TABLE LoL_Pros_02.dbo.Tournament (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	startDate date NULL,
	endDate date NULL,
	CONSTRAINT Tournament_PK PRIMARY KEY (tournamentId)
);
ALTER TABLE LoL_Pros_02.dbo.Tournament WITH NOCHECK ADD CONSTRAINT Tournament_CHECK CHECK (([endDate] IS NULL OR [endDate]>[startDate]));


-- LoL_Pros_02.dbo.InternationalTournament definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.InternationalTournament;

CREATE TABLE LoL_Pros_02.dbo.InternationalTournament (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT InternationalTournament_PK PRIMARY KEY (tournamentId),
	CONSTRAINT InternationalTournament_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros_02.dbo.Tournament(tournamentId)
);


-- LoL_Pros_02.dbo.ParentRegion definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.ParentRegion;

CREATE TABLE LoL_Pros_02.dbo.ParentRegion (
	minorRegionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	majorRegionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT ParentRegion_PK PRIMARY KEY (minorRegionName),
	CONSTRAINT ParentRegion_Region_FK FOREIGN KEY (minorRegionName) REFERENCES LoL_Pros_02.dbo.Region(regionName),
	CONSTRAINT ParentRegion_Region_FK_1 FOREIGN KEY (majorRegionName) REFERENCES LoL_Pros_02.dbo.Region(regionName)
);


-- LoL_Pros_02.dbo.RegionalTournament definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.RegionalTournament;

CREATE TABLE LoL_Pros_02.dbo.RegionalTournament (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	season varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	regionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT RegionalTournament_PK PRIMARY KEY (tournamentId),
	CONSTRAINT RegionalTournament_Region_FK FOREIGN KEY (regionName) REFERENCES LoL_Pros_02.dbo.Region(regionName),
	CONSTRAINT RegionalTournament_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros_02.dbo.Tournament(tournamentId)
);


-- LoL_Pros_02.dbo.Team definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.Team;

CREATE TABLE LoL_Pros_02.dbo.Team (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamCaptain varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	teamName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamLogo varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Team_PK PRIMARY KEY (teamId),
	CONSTRAINT Team_Player_FK FOREIGN KEY (teamCaptain) REFERENCES LoL_Pros_02.dbo.Player(ingameName) ON UPDATE CASCADE
);


-- LoL_Pros_02.dbo.TeamRegion definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.TeamRegion;

CREATE TABLE LoL_Pros_02.dbo.TeamRegion (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	regionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT TeamRegion_PK PRIMARY KEY (teamId,regionName),
	CONSTRAINT TeamRegion_Region_FK FOREIGN KEY (regionName) REFERENCES LoL_Pros_02.dbo.Region(regionName),
	CONSTRAINT TeamRegion_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros_02.dbo.Team(teamId)
);


-- LoL_Pros_02.dbo.TeamSponsors definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.TeamSponsors;

CREATE TABLE LoL_Pros_02.dbo.TeamSponsors (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	sponsor varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT teamSponsors_pk PRIMARY KEY (teamId,sponsor),
	CONSTRAINT teamSponsors_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros_02.dbo.Team(teamId)
);


-- LoL_Pros_02.dbo.TeamTournamentCompete definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.TeamTournamentCompete;

CREATE TABLE LoL_Pros_02.dbo.TeamTournamentCompete (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[rank] int NULL,
	CONSTRAINT TeamTournamentCompete_PK PRIMARY KEY (teamId,tournamentId),
	CONSTRAINT TeamTournamentCompete_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros_02.dbo.Team(teamId),
	CONSTRAINT TeamTournamentCompete_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros_02.dbo.Tournament(tournamentId)
);


-- LoL_Pros_02.dbo.TournamentGroup definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.TournamentGroup;

CREATE TABLE LoL_Pros_02.dbo.TournamentGroup (
	groupId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	groupName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Group_PK PRIMARY KEY (groupId),
	CONSTRAINT Group_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros_02.dbo.Tournament(tournamentId)
);


-- LoL_Pros_02.dbo.TournamentPlace definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.TournamentPlace;

CREATE TABLE LoL_Pros_02.dbo.TournamentPlace (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	locationCity varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	locationArena varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT TournamentPlace_PK PRIMARY KEY (tournamentId,locationCity,locationArena),
	CONSTRAINT TournamentPlace_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros_02.dbo.Tournament(tournamentId)
);


-- LoL_Pros_02.dbo.Trophy definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.Trophy;

CREATE TABLE LoL_Pros_02.dbo.Trophy (
	trophyName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	dateAwarded date NULL,
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT Trophy_PK PRIMARY KEY (trophyName,tournamentId),
	CONSTRAINT TeamTrophy_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros_02.dbo.Team(teamId),
	CONSTRAINT Trophy_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros_02.dbo.Tournament(tournamentId)
);


-- LoL_Pros_02.dbo.GameMatch definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.GameMatch;

CREATE TABLE LoL_Pros_02.dbo.GameMatch (
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	matchDate date NULL,
	teamAId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	teamBId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	resultTeamA varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	resultTeamB varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	bestOf varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Match_PK PRIMARY KEY (matchId),
	CONSTRAINT FK3ml3domli3316pwmqtt3qnpb0 FOREIGN KEY (teamAId) REFERENCES LoL_Pros_02.dbo.Team(teamId),
	CONSTRAINT FKrwtn0rvn70ce056uhgw1etrwh FOREIGN KEY (teamBId) REFERENCES LoL_Pros_02.dbo.Team(teamId),
	CONSTRAINT Match_Team_FK FOREIGN KEY (teamAId) REFERENCES LoL_Pros_02.dbo.Team(teamId),
	CONSTRAINT Match_Team_FK_1 FOREIGN KEY (teamBId) REFERENCES LoL_Pros_02.dbo.Team(teamId)
);
ALTER TABLE LoL_Pros_02.dbo.GameMatch WITH NOCHECK ADD CONSTRAINT Match_CHECK CHECK (([teamAId]<>[teamBId]));


-- LoL_Pros_02.dbo.GroupStageMatch definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.GroupStageMatch;

CREATE TABLE LoL_Pros_02.dbo.GroupStageMatch (
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	groupId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	isTieBreak bit NULL,
	CONSTRAINT GroupStageMatch_PK PRIMARY KEY (matchId),
	CONSTRAINT GroupStageMatch_Group_FK FOREIGN KEY (groupId) REFERENCES LoL_Pros_02.dbo.TournamentGroup(groupId),
	CONSTRAINT GroupStageMatch_Match_FK FOREIGN KEY (matchId) REFERENCES LoL_Pros_02.dbo.GameMatch(matchId)
);


-- LoL_Pros_02.dbo.KnockOutStageMatch definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.KnockOutStageMatch;

CREATE TABLE LoL_Pros_02.dbo.KnockOutStageMatch (
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	round varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT KnockOutStageMatch_PK PRIMARY KEY (matchId),
	CONSTRAINT KnockOutStageMatch_Match_FK FOREIGN KEY (matchId) REFERENCES LoL_Pros_02.dbo.GameMatch(matchId),
	CONSTRAINT KnockOutStageMatch_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros_02.dbo.Tournament(tournamentId)
);


-- LoL_Pros_02.dbo.PlayerTeamHistory definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.PlayerTeamHistory;

CREATE TABLE LoL_Pros_02.dbo.PlayerTeamHistory (
	playerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	startDate date NOT NULL,
	endDate date NULL,
	CONSTRAINT playerTeamHistory_pk PRIMARY KEY (playerId,teamId,startDate),
	CONSTRAINT playerTeamHistory_Player_FK FOREIGN KEY (playerId) REFERENCES LoL_Pros_02.dbo.Player(playerId),
	CONSTRAINT playerTeamHistory_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros_02.dbo.Team(teamId)
);
ALTER TABLE LoL_Pros_02.dbo.PlayerTeamHistory WITH NOCHECK ADD CONSTRAINT PlayerTeamHistory_CHECK CHECK (([endDate] IS NULL OR [endDate]>[startDate]));


-- LoL_Pros_02.dbo.SingleGame definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.SingleGame;

CREATE TABLE LoL_Pros_02.dbo.SingleGame (
	gameId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	gameNumber varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamWinnerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Game_PK PRIMARY KEY (gameId),
	CONSTRAINT Game_Match_FK FOREIGN KEY (matchId) REFERENCES LoL_Pros_02.dbo.GameMatch(matchId),
	CONSTRAINT Game_Team_FK FOREIGN KEY (teamWinnerId) REFERENCES LoL_Pros_02.dbo.Team(teamId)
);


-- LoL_Pros_02.dbo.TeamsInGroup definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.TeamsInGroup;

CREATE TABLE LoL_Pros_02.dbo.TeamsInGroup (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	groupId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	draw varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	win varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	loss varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT TeamsInGroup_PK PRIMARY KEY (teamId,groupId),
	CONSTRAINT TeamsInGroup_Group_FK FOREIGN KEY (groupId) REFERENCES LoL_Pros_02.dbo.TournamentGroup(groupId),
	CONSTRAINT TeamsInGroup_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros_02.dbo.Team(teamId)
);


-- LoL_Pros_02.dbo.PlayerPlayedGames definition

-- Drop table

-- DROP TABLE LoL_Pros_02.dbo.PlayerPlayedGames;

CREATE TABLE LoL_Pros_02.dbo.PlayerPlayedGames (
	playerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	gameId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PlayerPlayedGames_PK PRIMARY KEY (gameId,playerId),
	CONSTRAINT PlayerPlayedGames_Game_FK FOREIGN KEY (gameId) REFERENCES LoL_Pros_02.dbo.SingleGame(gameId),
	CONSTRAINT PlayerPlayedGames_Player_FK FOREIGN KEY (playerId) REFERENCES LoL_Pros_02.dbo.Player(playerId)
);

INSERT INTO LoL_Pros_02.dbo.Player (playerId,ingameName,playerFirstName,playerLastMiddleName,dob,nationality,[role]) VALUES
	 (N'PL0001',N'Chovy',N'Ji-hoon',N'Jeong','2001-03-03',N'South Korea',N'MID'),
	 (N'PL0002',N'Doran',N'Choi',N'Hyeon-joon','2000-07-22',N'South Korea',N'TOP'),
	 (N'PL0003',N'Peanut',N'Han',N'Wang-ho','1998-02-03',N'South Korea',N'JUNG'),
	 (N'PL0004',N'Ruler',N'Park',N'Jae-hyuk','1998-12-29',N'South Korea',N'AD'),
	 (N'PL0005',N'Lehends',N'Son',N'Si-woo','1998-12-24',N'South Korea',N'SP'),
	 (N'PL0006',N'Kiaya',N'Tran',N'Duy Sang','2001-05-17',N'Vietnam',N'TOP'),
	 (N'PL0007',N'Levi',N'Do',N'Duy Khanh','1997-11-18',N'Vietnam',N'JUNG'),
	 (N'PL0008',N'Kati',N'Dang',N'Thanh Phe','1999-11-05',N'Vietnam',N'MID'),
	 (N'PL0009',N'Sty1e',N'Mai',N'Hoang Son','2002-01-11',N'Vietnam',N'AD'),
	 (N'PL0010',N'Bie',N'Tran',N'Duc Hieu','1999-12-09',N'Vietnam',N'SP');
INSERT INTO LoL_Pros_02.dbo.Player (playerId,ingameName,playerFirstName,playerLastMiddleName,dob,nationality,[role]) VALUES
	 (N'PL0011',N'Zeus',NULL,NULL,NULL,N'South Korea',N'TOP'),
	 (N'PL0012',N'Oner',NULL,NULL,NULL,N'South Korea',N'JUNG'),
	 (N'PL0013',N'Faker',NULL,NULL,NULL,N'South Korea',N'MID'),
	 (N'PL0014',N'Gumayusi',NULL,NULL,NULL,N'South Korea',N'AD'),
	 (N'PL0015',N'Keria',NULL,NULL,NULL,N'South Korea',N'SP'),
	 (N'PL0016',N'Kingen',NULL,NULL,NULL,N'South Korea',N'TOP'),
	 (N'PL0017',N'Pyosik',NULL,NULL,NULL,N'South Korea',N'JUNG'),
	 (N'PL0018',N'Zeka',NULL,NULL,NULL,N'South Korea',N'MID'),
	 (N'PL0019',N'Deft',NULL,NULL,NULL,N'South Korea',N'AD'),
	 (N'PL0020',N'BeryL',NULL,NULL,NULL,N'South Korea',N'SP');

INSERT INTO LoL_Pros_02.dbo.Team (teamId,teamCaptain,teamName,teamLogo) VALUES
	 (N'TM0001',N'Chovy',N'Gen.G',N'https://drive.google.com/open?id=1KkNiGBfTvR7h-N7KQYMkwH_nHX-aQ2jw&usp=drive_fs'),
	 (N'TM0002',N'Levi',N'GAM Esports',N'https://drive.google.com/open?id=1Kk_lehbXWWSlkd0HNW7BYHtODmjVUmvC&usp=drive_fs'),
	 (N'TM0003',NULL,N'JD Gaming',NULL),
	 (N'TM0004',NULL,N'Rogue',NULL),
	 (N'TM0005',NULL,N'Cloud9',NULL),
	 (N'TM0006',NULL,N'Top Esports',NULL),
	 (N'TM0007',NULL,N'T1',NULL),
	 (N'TM0008',NULL,N'G2 Esports',NULL),
	 (N'TM0009',NULL,N'CTBC Flying Oyster',NULL),
	 (N'TM0010',NULL,N'EDward Gaming',NULL);
INSERT INTO LoL_Pros_02.dbo.Team (teamId,teamCaptain,teamName,teamLogo) VALUES
	 (N'TM0011',NULL,N'DWG KIA',NULL),
	 (N'TM0012',NULL,N'100 Thieves',NULL),
	 (N'TM0013',NULL,N'Fnatic',NULL),
	 (N'TM0014',NULL,N'DRX',NULL),
	 (N'TM0015',NULL,N'Evil Geniuses',NULL),
	 (N'TM0016',NULL,N'RNG',NULL);

INSERT INTO LoL_Pros_02.dbo.Region (regionName,description) VALUES
	 (N'CBLOL',NULL),
	 (N'LCK',NULL),
	 (N'LCL',NULL),
	 (N'LCO',NULL),
	 (N'LCS',NULL),
	 (N'LEC',NULL),
	 (N'LJL',NULL),
	 (N'LLA',NULL),
	 (N'LPL',NULL),
	 (N'PCS',NULL);
INSERT INTO LoL_Pros_02.dbo.Region (regionName,description) VALUES
	 (N'TCL',NULL),
	 (N'VCS',NULL);

INSERT INTO LoL_Pros_02.dbo.Tournament (tournamentId,tournamentName,startDate,endDate) VALUES
	 (N'TR0001',N'2022 World Championship','2022-10-07','2022-11-05'),
	 (N'TR0002',N'LCK 2022 Spring','2022-03-23','2022-04-02'),
	 (N'TR0003',N'LCK 2022 Summer','2022-08-17','2022-08-28'),
	 (N'TR0004',N'2022 Mid-Season Invitational','2022-05-10','2022-05-29'),
	 (N'TR0005',N'2023 Mid-Season Invitational','2023-05-02','2023-05-21'),
	 (N'TR0006',N'2023 World Championship','2023-10-10','2023-11-19'),
	 (N'TR0007',N'LCK 2023 Spring Season','2023-01-18','2023-03-19'),
	 (N'TR0008',N'LCK 2023 Summer Season','2023-06-07','2023-08-06');

INSERT INTO LoL_Pros_02.dbo.TeamSponsors (teamId,sponsor) VALUES
	 (N'TM0001',N'	Afreecatv'),
	 (N'TM0001',N'LG UltraGear'),
	 (N'TM0001',N'MONSTERENERGY'),
	 (N'TM0001',N'PUMA'),
	 (N'TM0001',N'ROCCAT');

INSERT INTO LoL_Pros_02.dbo.PlayerTeamHistory (playerId,teamId,startDate,endDate) VALUES
	 (N'PL0001',N'TM0001','2021-11-24','2023-11-21'),
	 (N'PL0001',N'TM0001','2024-11-20',NULL),
	 (N'PL0002',N'TM0001','2021-11-23','2023-11-21'),
	 (N'PL0002',N'TM0007','2024-11-19',NULL),
	 (N'PL0003',N'TM0008','2024-11-19',NULL),
	 (N'PL0004',N'TM0001','2018-05-04','2022-11-10'),
	 (N'PL0004',N'TM0001','2024-11-20',NULL),
	 (N'PL0005',N'TM0008','2024-11-19',NULL),
	 (N'PL0006',N'TM0002','2020-01-05',NULL),
	 (N'PL0007',N'TM0002','2019-05-15',NULL);
INSERT INTO LoL_Pros_02.dbo.PlayerTeamHistory (playerId,teamId,startDate,endDate) VALUES
	 (N'PL0008',N'TM0016','2024-11-19',NULL),
	 (N'PL0009',N'TM0016','2024-11-19',NULL),
	 (N'PL0010',N'TM0015','2024-11-19',NULL),
	 (N'PL0011',N'TM0006','2024-11-19',NULL),
	 (N'PL0012',N'TM0007','2024-11-19',NULL),
	 (N'PL0013',N'TM0007','2024-11-19',NULL),
	 (N'PL0014',N'TM0007','2024-11-19',NULL),
	 (N'PL0015',N'TM0007','2024-11-19',NULL),
	 (N'PL0016',N'TM0011','2024-11-19',NULL),
	 (N'PL0017',N'TM0011','2024-11-19',NULL);
INSERT INTO LoL_Pros_02.dbo.PlayerTeamHistory (playerId,teamId,startDate,endDate) VALUES
	 (N'PL0018',N'TM0011','2024-11-19',NULL),
	 (N'PL0019',N'TM0011','2024-11-19',NULL),
	 (N'PL0020',N'TM0011','2024-11-19',NULL);



INSERT INTO LoL_Pros_02.dbo.InternationalTournament (tournamentId) VALUES
	 (N'TR0001'),
	 (N'TR0004'),
	 (N'TR0005'),
	 (N'TR0006');

INSERT INTO LoL_Pros_02.dbo.RegionalTournament (tournamentId,season,regionName) VALUES
	 (N'TR0002',N'Spring',N'LCK'),
	 (N'TR0003',N'Summer',N'LCK'),
	 (N'TR0007',N'Spring',N'LCK'),
	 (N'TR0008',N'Summer',N'LCK');

INSERT INTO LoL_Pros_02.dbo.TournamentPlace (tournamentId,locationCity,locationArena) VALUES
	 (N'TR0001',N'Atlanta',N'State Farm Arena'),
	 (N'TR0001',N'Mexico City',N'Arena Esports Stadium (LLA Studio)'),
	 (N'TR0001',N'New York',N'Hulu Theatre at Madison Square Garden'),
	 (N'TR0001',N'San Francisco',N'Chase Center'),
	 (N'TR0002',N'Seoul',N'LoL Park'),
	 (N'TR0003',N'Seoul',N'LoL Park'),
	 (N'TR0004',N'Busan',N'Busan Esports Arena	'),
	 (N'TR0004',N'Busan',N'Busan Exhibition and Convention Center'),
	 (N'TR0007',N'Seoul',N'LoL Park'),
	 (N'TR0008',N'Seoul',N'LoL Park');

INSERT INTO LoL_Pros_02.dbo.Trophy (trophyName,dateAwarded,teamId,tournamentId) VALUES
	 (N'LCK Cup','2022-03-20',N'TM0007',N'TR0002'),
	 (N'LCK Cup','2022-08-14',N'TM0001',N'TR0003'),
	 (N'LCK Cup','2023-08-14',N'TM0001',N'TR0007'),
	 (N'LCK Cup','2023-03-20',N'TM0001',N'TR0008'),
	 (N'World Championship','2022-11-05',N'TM0014',N'TR0001'),
	 (N'World Championship','2023-11-19',N'TM0007',N'TR0006');

INSERT INTO LoL_Pros_02.dbo.TournamentGroup (groupId,groupName,tournamentId) VALUES
	 (N'GR0001',N'Group A',N'TR0001'),
	 (N'GR0002',N'Group B',N'TR0001'),
	 (N'GR0003',N'Group C',N'TR0001'),
	 (N'GR0004',N'Group D',N'TR0001');

INSERT INTO LoL_Pros_02.dbo.TeamsInGroup (teamId,groupId,draw,win,loss) VALUES
	 (N'TM0001',N'GR0004',N'0',N'5',N'1'),
	 (N'TM0002',N'GR0003',N'0',N'1',N'5'),
	 (N'TM0003',N'GR0002',N'0',N'5',N'1'),
	 (N'TM0004',N'GR0003',N'0',N'4',N'2'),
	 (N'TM0005',N'GR0001',N'0',N'1',N'5'),
	 (N'TM0006',N'GR0003',N'0',N'3',N'3'),
	 (N'TM0007',N'GR0001',N'0',N'5',N'1'),
	 (N'TM0008',N'GR0002',N'0',N'1',N'5'),
	 (N'TM0009',N'GR0004',N'0',N'1',N'5'),
	 (N'TM0010',N'GR0001',N'0',N'4',N'2');
INSERT INTO LoL_Pros_02.dbo.TeamsInGroup (teamId,groupId,draw,win,loss) VALUES
	 (N'TM0011',N'GR0002',N'0',N'5',N'1'),
	 (N'TM0012',N'GR0004',N'0',N'1',N'5'),
	 (N'TM0013',N'GR0001',N'0',N'2',N'4'),
	 (N'TM0014',N'GR0003',N'0',N'4',N'2'),
	 (N'TM0015',N'GR0002',N'0',N'1',N'5'),
	 (N'TM0016',N'GR0004',N'0',N'5',N'1');



INSERT INTO LoL_Pros_02.dbo.GameMatch (matchId,matchDate,teamAId,teamBId,resultTeamA,resultTeamB,bestOf) VALUES
	 (N'MT0001','2022-11-06',N'TM0014',N'TM0007',N'3',N'2',N'5'),
	 (N'MT0002','2022-10-30',N'TM0014',N'TM0001',N'3',N'1',N'5'),
	 (N'MT0003','2022-10-30',N'TM0003',N'TM0007',N'1',N'3',N'5'),
	 (N'MT0004','2022-10-24',N'TM0014',N'TM0010',N'3',N'2',N'5'),
	 (N'MT0005','2022-10-22',N'TM0001',N'TM0011',N'3',N'2',N'5'),
	 (N'MT0006','2022-10-22',N'TM0007',N'TM0016',N'3',N'0',N'5'),
	 (N'MT0007','2022-10-22',N'TM0003',N'TM0004',N'3',N'0',N'5'),
	 (N'MT0008','2022-10-17',N'TM0001',N'TM0016',N'1',N'0',N'1'),
	 (N'MT0009','2022-10-16',N'TM0014',N'TM0004',N'1',N'0',N'1'),
	 (N'MT0010','2022-10-15',N'TM0003',N'TM0011',N'1',N'0',N'1');

INSERT INTO LoL_Pros_02.dbo.SingleGame (gameId,gameNumber,matchId,teamWinnerId) VALUES
	 (N'GM0001',N'1',N'MT0001',N'TM0007'),
	 (N'GM0002',N'2',N'MT0001',N'TM0014'),
	 (N'GM0003',N'3',N'MT0001',N'TM0007'),
	 (N'GM0004',N'4',N'MT0001',N'TM0014'),
	 (N'GM0005',N'5',N'MT0001',N'TM0014');

INSERT INTO LoL_Pros_02.dbo.PlayerPlayedGames (playerId,gameId) VALUES
	 (N'PL0011',N'GM0001'),
	 (N'PL0012',N'GM0001'),
	 (N'PL0013',N'GM0001'),
	 (N'PL0014',N'GM0001'),
	 (N'PL0015',N'GM0001'),
	 (N'PL0016',N'GM0001'),
	 (N'PL0017',N'GM0001'),
	 (N'PL0018',N'GM0001'),
	 (N'PL0019',N'GM0001'),
	 (N'PL0020',N'GM0001');

INSERT INTO LoL_Pros_02.dbo.GroupStageMatch (matchId,groupId,isTieBreak) VALUES
	 (N'MT0008',N'GR0004',1),
	 (N'MT0009',N'GR0003',1),
	 (N'MT0010',N'GR0002',1);

INSERT INTO LoL_Pros_02.dbo.KnockOutStageMatch (matchId,tournamentId,round) VALUES
	 (N'MT0001',N'TR0001',N'Final'),
	 (N'MT0002',N'TR0001',N'Semi-finals'),
	 (N'MT0003',N'TR0001',N'Semi-finals'),
	 (N'MT0004',N'TR0001',N'Quarter-finals'),
	 (N'MT0005',N'TR0001',N'Quarter-finals'),
	 (N'MT0006',N'TR0001',N'Quarter-finals'),
	 (N'MT0007',N'TR0001',N'Quarter-finals');

INSERT INTO LoL_Pros_02.dbo.TeamTournamentCompete (teamId,tournamentId,[rank]) VALUES
	 (N'TM0001',N'TR0001',4),
	 (N'TM0002',N'TR0001',16),
	 (N'TM0003',N'TR0001',4),
	 (N'TM0004',N'TR0001',16),
	 (N'TM0005',N'TR0001',16),
	 (N'TM0006',N'TR0001',16),
	 (N'TM0007',N'TR0001',2),
	 (N'TM0008',N'TR0001',16),
	 (N'TM0009',N'TR0001',16),
	 (N'TM0010',N'TR0001',16);
INSERT INTO LoL_Pros_02.dbo.TeamTournamentCompete (teamId,tournamentId,[rank]) VALUES
	 (N'TM0011',N'TR0001',16),
	 (N'TM0012',N'TR0001',16),
	 (N'TM0013',N'TR0001',16),
	 (N'TM0014',N'TR0001',1),
	 (N'TM0015',N'TR0001',16),
	 (N'TM0016',N'TR0001',16);

INSERT INTO LoL_Pros_02.dbo.TeamRegion (teamId,regionName) VALUES
	 (N'TM0001',N'LCK'),
	 (N'TM0002',N'VCS'),
	 (N'TM0003',N'LPL'),
	 (N'TM0004',N'LEC'),
	 (N'TM0005',N'LCS'),
	 (N'TM0006',N'LPL'),
	 (N'TM0007',N'LCK'),
	 (N'TM0008',N'LEC'),
	 (N'TM0009',N'PCS'),
	 (N'TM0010',N'LPL');
INSERT INTO LoL_Pros_02.dbo.TeamRegion (teamId,regionName) VALUES
	 (N'TM0011',N'LCK'),
	 (N'TM0012',N'LCS'),
	 (N'TM0013',N'LEC'),
	 (N'TM0014',N'LCK'),
	 (N'TM0015',N'LCS'),
	 (N'TM0016',N'LPL');

--PROCEDURE----------------------
GO
--Từ khóa GO không phải là câu lệnh SQL mà là chỉ thị của công cụ SQL Server Management Studio (SSMS) hoặc SQLCMD để kết thúc batch
CREATE PROCEDURE dbo.Update_PlayerTeam 
@p_playerId Char(50), @p_teamId Char(50)
AS 
BEGIN
	--SET NOCOUNT ON;
	DECLARE @teamId_old char(50);
	
	BEGIN TRANSACTION;
	SELECT 
	    @teamId_old = teamId
	FROM 
	    LoL_Pros_02.dbo.PlayerTeamHistory 
	WHERE 
	    playerId = @p_playerId
	    AND endDate IS NULL;
	   
	UPDATE LoL_Pros_02.dbo.PlayerTeamHistory
	   SET endDate = CONVERT(date, GETDATE() -1)
	   WHERE 
	    playerId = @p_playerId
	    AND endDate IS NULL
	   AND teamId = @teamId_old;
	INSERT INTO LoL_Pros_02.dbo.PlayerTeamHistory (playerId,teamId,startDate,endDate) VALUES
		 (TRIM(@p_playerId),TRIM(@p_teamId),CONVERT(date, GETDATE()),NULL);
	COMMIT TRANSACTION;
END
GO
--PROCEDURE END---------------

-- TRIGGER CREATE-----
GO
CREATE TRIGGER trg_CheckRegionMatch
ON LoL_Pros_02.dbo.TeamTournamentCompete
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Kiểm tra xem dữ liệu được thêm vào có khớp giữa TeamRegion và RegionalTournament
    IF EXISTS (
        SELECT 1
        FROM INSERTED i
        JOIN LoL_Pros_02.dbo.TeamRegion tr ON i.teamId = tr.teamId
        JOIN LoL_Pros_02.dbo.RegionalTournament rt ON i.tournamentId = rt.tournamentId
        WHERE tr.regionName != rt.regionName
    )
    BEGIN
        -- Trả về lỗi và huỷ thao tác nếu regionName không khớp
        RAISERROR ('Region mismatch: TeamRegion and RegionalTournament regions do not match for the given teamId and tournamentId.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Chèn dữ liệu nếu kiểm tra thành công
    INSERT INTO LoL_Pros_02.dbo.TeamTournamentCompete (teamId, tournamentId, [rank])
    SELECT teamId, tournamentId, [rank]
    FROM INSERTED;
END
GO
--TRIGGER END --------------------------