CREATE TABLE [dbo].[Registration]
(
  [registration_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [student_id] INT NOT NULL FOREIGN KEY REFERENCES dbo.[Student](matr_nr),
  [course_number] INT NOT NULL FOREIGN KEY REFERENCES dbo.[Course](course_number),
  [study_id] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[StudyProgram](study_id),
  [active] BIT NOT NULL DEFAULT 1,
  [grade] VARCHAR,
  [semester] VARCHAR(10) NOT NULL,
)
