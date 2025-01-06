-- DROP SCHEMA dbo;

-- CREATE SCHEMA dbo;
-- LoL_Pros.dbo.Player definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Player;

CREATE TABLE LoL_Pros.dbo.Player (
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
ALTER TABLE LoL_Pros.dbo.Player WITH NOCHECK ADD CONSTRAINT Player_CHECK CHECK (([role]='SP' OR [role]='AD' OR [role]='MID' OR [role]='JUNG' OR [role]='TOP'));


-- LoL_Pros.dbo.Region definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Region;

CREATE TABLE LoL_Pros.dbo.Region (
	regionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	description varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Region_PK PRIMARY KEY (regionName)
);


-- LoL_Pros.dbo.Tournament definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Tournament;

CREATE TABLE LoL_Pros.dbo.Tournament (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	startDate date NULL,
	endDate date NULL,
	CONSTRAINT Tournament_PK PRIMARY KEY (tournamentId)
);
ALTER TABLE LoL_Pros.dbo.Tournament WITH NOCHECK ADD CONSTRAINT Tournament_CHECK CHECK (([endDate] IS NULL OR [endDate]>[startDate]));


-- LoL_Pros.dbo.InternationalTournament definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.InternationalTournament;

CREATE TABLE LoL_Pros.dbo.InternationalTournament (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT InternationalTournament_PK PRIMARY KEY (tournamentId),
	CONSTRAINT InternationalTournament_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.ParentRegion definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.ParentRegion;

CREATE TABLE LoL_Pros.dbo.ParentRegion (
	minorRegionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	majorRegionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT ParentRegion_PK PRIMARY KEY (minorRegionName),
	CONSTRAINT ParentRegion_Region_FK FOREIGN KEY (minorRegionName) REFERENCES LoL_Pros.dbo.Region(regionName),
	CONSTRAINT ParentRegion_Region_FK_1 FOREIGN KEY (majorRegionName) REFERENCES LoL_Pros.dbo.Region(regionName)
);


-- LoL_Pros.dbo.RegionalTournament definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.RegionalTournament;

CREATE TABLE LoL_Pros.dbo.RegionalTournament (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	season varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	regionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT RegionalTournament_PK PRIMARY KEY (tournamentId),
	CONSTRAINT RegionalTournament_Region_FK FOREIGN KEY (regionName) REFERENCES LoL_Pros.dbo.Region(regionName),
	CONSTRAINT RegionalTournament_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.Team definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Team;

CREATE TABLE LoL_Pros.dbo.Team (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamCaptain varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	teamName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamLogo varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Team_PK PRIMARY KEY (teamId),
	CONSTRAINT Team_Player_FK FOREIGN KEY (teamCaptain) REFERENCES LoL_Pros.dbo.Player(ingameName) ON UPDATE CASCADE
);


-- LoL_Pros.dbo.TeamRegion definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TeamRegion;

CREATE TABLE LoL_Pros.dbo.TeamRegion (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	regionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT TeamRegion_PK PRIMARY KEY (teamId,regionName),
	CONSTRAINT TeamRegion_Region_FK FOREIGN KEY (regionName) REFERENCES LoL_Pros.dbo.Region(regionName),
	CONSTRAINT TeamRegion_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros.dbo.Team(teamId)
);


-- LoL_Pros.dbo.TeamSponsors definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TeamSponsors;

CREATE TABLE LoL_Pros.dbo.TeamSponsors (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	sponsor varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT teamSponsors_pk PRIMARY KEY (teamId,sponsor),
	CONSTRAINT teamSponsors_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros.dbo.Team(teamId)
);


-- LoL_Pros.dbo.TeamTournamentCompete definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TeamTournamentCompete;

CREATE TABLE LoL_Pros.dbo.TeamTournamentCompete (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[rank] int NULL,
	CONSTRAINT TeamTournamentCompete_PK PRIMARY KEY (teamId,tournamentId),
	CONSTRAINT TeamTournamentCompete_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros.dbo.Team(teamId),
	CONSTRAINT TeamTournamentCompete_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.TournamentGroup definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TournamentGroup;

CREATE TABLE LoL_Pros.dbo.TournamentGroup (
	groupId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	groupName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Group_PK PRIMARY KEY (groupId),
	CONSTRAINT Group_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.TournamentPlace definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TournamentPlace;

CREATE TABLE LoL_Pros.dbo.TournamentPlace (
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	locationCity varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	locationArena varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT TournamentPlace_PK PRIMARY KEY (tournamentId,locationCity,locationArena),
	CONSTRAINT TournamentPlace_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.Trophy definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Trophy;

CREATE TABLE LoL_Pros.dbo.Trophy (
	trophyName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	dateAwarded date NULL,
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT Trophy_PK PRIMARY KEY (trophyName,tournamentId),
	CONSTRAINT TeamTrophy_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros.dbo.Team(teamId),
	CONSTRAINT Trophy_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.GameMatch definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.GameMatch;

CREATE TABLE LoL_Pros.dbo.GameMatch (
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	matchDate date NULL,
	teamAId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	teamBId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	resultTeamA varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	resultTeamB varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	bestOf varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Match_PK PRIMARY KEY (matchId),
	CONSTRAINT FK3ml3domli3316pwmqtt3qnpb0 FOREIGN KEY (teamAId) REFERENCES LoL_Pros.dbo.Team(teamId),
	CONSTRAINT FKrwtn0rvn70ce056uhgw1etrwh FOREIGN KEY (teamBId) REFERENCES LoL_Pros.dbo.Team(teamId),
	CONSTRAINT Match_Team_FK FOREIGN KEY (teamAId) REFERENCES LoL_Pros.dbo.Team(teamId),
	CONSTRAINT Match_Team_FK_1 FOREIGN KEY (teamBId) REFERENCES LoL_Pros.dbo.Team(teamId)
);
ALTER TABLE LoL_Pros.dbo.GameMatch WITH NOCHECK ADD CONSTRAINT Match_CHECK CHECK (([teamAId]<>[teamBId]));


-- LoL_Pros.dbo.GroupStageMatch definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.GroupStageMatch;

CREATE TABLE LoL_Pros.dbo.GroupStageMatch (
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	groupId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	isTieBreak bit NULL,
	CONSTRAINT GroupStageMatch_PK PRIMARY KEY (matchId),
	CONSTRAINT GroupStageMatch_Group_FK FOREIGN KEY (groupId) REFERENCES LoL_Pros.dbo.TournamentGroup(groupId),
	CONSTRAINT GroupStageMatch_Match_FK FOREIGN KEY (matchId) REFERENCES LoL_Pros.dbo.GameMatch(matchId)
);


-- LoL_Pros.dbo.KnockOutStageMatch definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.KnockOutStageMatch;

CREATE TABLE LoL_Pros.dbo.KnockOutStageMatch (
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	round varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT KnockOutStageMatch_PK PRIMARY KEY (matchId),
	CONSTRAINT KnockOutStageMatch_Match_FK FOREIGN KEY (matchId) REFERENCES LoL_Pros.dbo.GameMatch(matchId),
	CONSTRAINT KnockOutStageMatch_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.PlayerTeamHistory definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.PlayerTeamHistory;

CREATE TABLE LoL_Pros.dbo.PlayerTeamHistory (
	playerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	startDate date NOT NULL,
	endDate date NULL,
	CONSTRAINT playerTeamHistory_pk PRIMARY KEY (playerId,teamId,startDate),
	CONSTRAINT playerTeamHistory_Player_FK FOREIGN KEY (playerId) REFERENCES LoL_Pros.dbo.Player(playerId),
	CONSTRAINT playerTeamHistory_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros.dbo.Team(teamId)
);
ALTER TABLE LoL_Pros.dbo.PlayerTeamHistory WITH NOCHECK ADD CONSTRAINT PlayerTeamHistory_CHECK CHECK (([endDate] IS NULL OR [endDate]>[startDate]));


-- LoL_Pros.dbo.SingleGame definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.SingleGame;

CREATE TABLE LoL_Pros.dbo.SingleGame (
	gameId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	gameNumber varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamWinnerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Game_PK PRIMARY KEY (gameId),
	CONSTRAINT Game_Match_FK FOREIGN KEY (matchId) REFERENCES LoL_Pros.dbo.GameMatch(matchId),
	CONSTRAINT Game_Team_FK FOREIGN KEY (teamWinnerId) REFERENCES LoL_Pros.dbo.Team(teamId)
);


-- LoL_Pros.dbo.TeamsInGroup definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TeamsInGroup;

CREATE TABLE LoL_Pros.dbo.TeamsInGroup (
	teamId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	groupId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	draw varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	win varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	loss varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT TeamsInGroup_PK PRIMARY KEY (teamId,groupId),
	CONSTRAINT TeamsInGroup_Group_FK FOREIGN KEY (groupId) REFERENCES LoL_Pros.dbo.TournamentGroup(groupId),
	CONSTRAINT TeamsInGroup_Team_FK FOREIGN KEY (teamId) REFERENCES LoL_Pros.dbo.Team(teamId)
);


-- LoL_Pros.dbo.PlayerPlayedGames definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.PlayerPlayedGames;

CREATE TABLE LoL_Pros.dbo.PlayerPlayedGames (
	playerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	gameId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT PlayerPlayedGames_PK PRIMARY KEY (gameId,playerId),
	CONSTRAINT PlayerPlayedGames_Game_FK FOREIGN KEY (gameId) REFERENCES LoL_Pros.dbo.SingleGame(gameId),
	CONSTRAINT PlayerPlayedGames_Player_FK FOREIGN KEY (playerId) REFERENCES LoL_Pros.dbo.Player(playerId)
);