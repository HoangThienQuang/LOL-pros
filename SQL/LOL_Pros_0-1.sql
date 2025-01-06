-- DROP SCHEMA dbo;

CREATE SCHEMA dbo;
-- LoL_Pros.dbo.Player definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Player;

CREATE TABLE LoL_Pros.dbo.Player (
	ingameName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	playerFirstName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	playerLastMiddleName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	playerId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	dob date NULL,
	nationality varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[role] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT Player_PK PRIMARY KEY (playerId)
);


-- LoL_Pros.dbo.Region definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Region;

CREATE TABLE LoL_Pros.dbo.Region (
	regionName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
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
	regionTournamentSeason varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	tournamentRegion varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT RegionalTournament_PK PRIMARY KEY (tournamentId),
	CONSTRAINT RegionalTournament_Region_FK FOREIGN KEY (tournamentRegion) REFERENCES LoL_Pros.dbo.Region(regionName),
	CONSTRAINT RegionalTournament_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.Team definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.Team;

CREATE TABLE LoL_Pros.dbo.Team (
	teamName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamCaptain varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Team_PK PRIMARY KEY (teamName),
	CONSTRAINT Team_Player_FK FOREIGN KEY (teamCaptain) REFERENCES LoL_Pros.dbo.Player(playerId)
);


-- LoL_Pros.dbo.TeamRegion definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TeamRegion;

CREATE TABLE LoL_Pros.dbo.TeamRegion (
	teamName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	regionOfTeam varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT TeamRegion_PK PRIMARY KEY (teamName,regionOfTeam),
	CONSTRAINT TeamRegion_Region_FK FOREIGN KEY (regionOfTeam) REFERENCES LoL_Pros.dbo.Region(regionName),
	CONSTRAINT TeamRegion_Team_FK FOREIGN KEY (teamName) REFERENCES LoL_Pros.dbo.Team(teamName)
);


-- LoL_Pros.dbo.TeamSponsors definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TeamSponsors;

CREATE TABLE LoL_Pros.dbo.TeamSponsors (
	teamName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	sponsor varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT teamSponsors_pk PRIMARY KEY (teamName,sponsor),
	CONSTRAINT teamSponsors_Team_FK FOREIGN KEY (teamName) REFERENCES LoL_Pros.dbo.Team(teamName)
);


-- LoL_Pros.dbo.TeamTournamentCompete definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.TeamTournamentCompete;

CREATE TABLE LoL_Pros.dbo.TeamTournamentCompete (
	teamName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	tournamentId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[rank] int NULL,
	CONSTRAINT TeamTournamentCompete_PK PRIMARY KEY (teamName,tournamentId),
	CONSTRAINT TeamTournamentCompete_Team_FK FOREIGN KEY (teamName) REFERENCES LoL_Pros.dbo.Team(teamName),
	CONSTRAINT TeamTournamentCompete_Tournament_FK FOREIGN KEY (tournamentId) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
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
	teamAwarded varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	trophyOfTournament varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	CONSTRAINT Trophy_PK PRIMARY KEY (trophyName,trophyOfTournament),
	CONSTRAINT TeamTrophy_Team_FK FOREIGN KEY (teamAwarded) REFERENCES LoL_Pros.dbo.Team(teamName),
	CONSTRAINT Trophy_Tournament_FK FOREIGN KEY (trophyOfTournament) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.[Match] definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.[Match];

CREATE TABLE LoL_Pros.dbo.[Match] (
	matchId varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	matchDate date NULL,
	matchInTournament varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	matchTeamA varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	matchTeamB varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	resultTeamA varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	resultTeamB varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT Match_PK PRIMARY KEY (matchId),
	CONSTRAINT Match_Team_FK FOREIGN KEY (matchTeamA) REFERENCES LoL_Pros.dbo.Team(teamName),
	CONSTRAINT Match_Team_FK_1 FOREIGN KEY (matchTeamB) REFERENCES LoL_Pros.dbo.Team(teamName),
	CONSTRAINT Match_Tournament_FK FOREIGN KEY (matchInTournament) REFERENCES LoL_Pros.dbo.Tournament(tournamentId)
);


-- LoL_Pros.dbo.PlayerTeamHistory definition

-- Drop table

-- DROP TABLE LoL_Pros.dbo.PlayerTeamHistory;

CREATE TABLE LoL_Pros.dbo.PlayerTeamHistory (
	playerName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	teamName varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	startDate date NOT NULL,
	endDate date NULL,
	CONSTRAINT playerTeamHistory_pk PRIMARY KEY (playerName,teamName,startDate),
	CONSTRAINT playerTeamHistory_Player_FK FOREIGN KEY (playerName) REFERENCES LoL_Pros.dbo.Player(playerId),
	CONSTRAINT playerTeamHistory_Team_FK FOREIGN KEY (teamName) REFERENCES LoL_Pros.dbo.Team(teamName)
);