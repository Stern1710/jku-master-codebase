CREATE TABLE [dbo].[Semester]
(
  [semester_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [studium_id] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[Studium](studium_id),
  [semesternummer] INT NOT NULL
);
