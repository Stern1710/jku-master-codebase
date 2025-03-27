CREATE TABLE [dbo].[Exam]
(
  [exam_sk] INT NOT NULL PRIMARY KEY IDENTITY,
  [source_id] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[University](university_id),
  [grade] INT NOT NULL,
  [year] VARCHAR(100) NOT NULL,
  [semester] VARCHAR(100) NOT NULL,
  [month] VARCHAR(100) NOT NULL
)
