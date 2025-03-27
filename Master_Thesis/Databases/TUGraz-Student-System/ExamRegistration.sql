CREATE TABLE [dbo].[ExamRegistration]
(
  [exam_registration_ID] INT NOT NULL PRIMARY KEY IDENTITY,
  [student_id] INT NOT NULL FOREIGN KEY REFERENCES [Student](student_id),
  [exam_id] INT NOT NULL FOREIGN KEY REFERENCES [Exam](exam_id),
  [grade] INT
)
