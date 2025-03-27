CREATE TABLE [dbo].[Course]
(
  [course_sk] INT NOT NULL PRIMARY KEY IDENTITY,
  [source_id] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[University](university_id),
  [institute] VARCHAR(100),
  [course_name] VARCHAR(100),
  [ects] FLOAT NOT NULL
)
