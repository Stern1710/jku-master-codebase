CREATE TABLE [dbo].[Student]
(
  [student_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [matr_nr] INT NOT NULL,
  [full_name] VARCHAR(200) NOT NULL,
  [birthday] BIGINT NOT NULL,
  [matr_date] BIGINT NOT NULL,
  [field_of_study] INT NOT NULL FOREIGN KEY REFERENCES [StudyField](study_id),
  [gender] BIT,
  [display_name] VARCHAR(100),
  [full_address] VARCHAR(300) NOT NULL
)
