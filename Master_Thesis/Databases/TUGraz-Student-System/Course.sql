CREATE TABLE [dbo].[Course]
(
  [course_id] INT NOT NULL PRIMARY KEY,
  [name] VARCHAR(100) NOT NULL,
  [ects] FLOAT NOT NULL,
  [max_people] INT NOT NULL,
  [institute] VARCHAR(100),
  [professor1] VARCHAR(100) NOT NULL,
  [professor2] VARCHAR(100),
  [other_professor] VARCHAR(300)
)
