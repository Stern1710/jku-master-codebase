CREATE TABLE [dbo].[StudyProgram]
(
  [study_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [name] VARCHAR(50) NOT NULL,
  [curr_link] VARCHAR(3000) NOT NULL,
  [faculty] VARCHAR(200) NOT NULL,
  [since] DATE NOT NULL,
  [deprecated_since] DATE
)
