CREATE TABLE [dbo].[Course]
(
  [course_number] INT NOT NULL PRIMARY KEY,
  [course_name] VARCHAR(50) NOT NULL,
  [max_capacity] INT,
  [ects] FLOAT NOT NULL,
  [location] VARCHAR(100) NOT NULL
);
