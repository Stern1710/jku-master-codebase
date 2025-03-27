CREATE TABLE [dwh].[Fact_ExamPerformance]
(
  [performance_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [grade] INT NOT NULL,
  [success] BIT NOT NULL,
  [ects] FLOAT NOT NULL,
  [dim_student_id] INT FOREIGN KEY REFERENCES [dwh].[Dim_Student](dim_student_id),
  [dim_Course_id] INT FOREIGN KEY REFERENCES [dwh].[Dim_Course](dim_course_id),
  [dim_study_id] INT FOREIGN KEY REFERENCES [dwh].[Dim_Study](dim_study_id),
  [dim_date_id] INT FOREIGN KEY REFERENCES [dwh].[Dim_Date](dim_date_id)
)
