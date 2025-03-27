CREATE TABLE [dbo].[Registration]
(
  [course_sk] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[Course](course_sk),
  [exam_sk] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[Exam](exam_sk),
  [student_sk] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[Student](student_sk),
  [study_sk] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[Study](study_sk),
  /* Constraints on composite primary key */
  PRIMARY KEY (course_sk, exam_sk, student_sk, study_sk)
)
