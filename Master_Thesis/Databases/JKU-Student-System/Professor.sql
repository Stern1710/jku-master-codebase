CREATE TABLE [dbo].[Professor]
(
  [professor_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [name] VARCHAR(100) NOT NULL,
  [institute] VARCHAR(50) NOT NULL,
  [title] VARCHAR(100)
);
