CREATE TABLE [dbo].[StudyField]
(
  [study_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [study_nr] VARCHAR NOT NULL,
  [name] VARCHAR(100) NOT NULL,
  [since_date] BIGINT NOT NULL,
  [faculty_id] INT NOT NULL FOREIGN KEY REFERENCES [Faculty](faculty_id)
)
