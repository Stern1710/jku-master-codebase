CREATE TABLE [dbo].[Faculty]
(
  [faculty_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [name] VARCHAR(50) NOT NULL,
  [head_professor] VARCHAR(100)
)
