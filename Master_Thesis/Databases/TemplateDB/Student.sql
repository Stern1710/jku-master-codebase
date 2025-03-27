CREATE TABLE [dbo].[Student]
(
  [student_sk] INT NOT NULL PRIMARY KEY IDENTITY,
  [source_id] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[University](university_id),
  [gender] VARCHAR(100) NOT NULL,
  [country] VARCHAR(100) NOT NULL,
  [city] VARCHAR(100) NOT NULL
)
