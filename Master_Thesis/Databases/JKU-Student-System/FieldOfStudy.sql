CREATE TABLE [dbo].[FieldOfStudy]
(
  [matr_nr] INT NOT NULL FOREIGN KEY REFERENCES [Student](matr_nr),
  [study_id] INT NOT NULL FOREIGN KEY REFERENCES [StudyProgram](study_id),
  [is_primary] BIT DEFAULT 1,
  PRIMARY KEY (matr_nr, study_id)
)
