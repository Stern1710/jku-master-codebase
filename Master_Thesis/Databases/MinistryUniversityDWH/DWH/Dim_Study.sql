CREATE TABLE [dwh].[Dim_Study]
(
  [dim_study_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [faculty] VARCHAR(100) NOT NULL,
  [study_program] VARCHAR(100) NOT NULL
)
