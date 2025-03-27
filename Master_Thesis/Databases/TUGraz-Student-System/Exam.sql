CREATE TABLE [dbo].[Exam]
(
  [exam_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [course_id] INT NOT NULL FOREIGN KEY REFERENCES [Course](course_id),
  [time_from] BIGINT NOT NULL,
  [time_to] BIGINT NOT NULL,
  [room] VARCHAR(50) NOT NULL
)
