CREATE TABLE [dbo].[Study]
(
  [study_sk] INT NOT NULL PRIMARY KEY IDENTITY,
  [source_id] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[University](university_id),
  [faculty] VARCHAR(100) NOT NULL,
  [study_program] VARCHAR(100) NOT NULL
)
