CREATE TABLE [dbo].[Teaching]
(
  [professor_id] INT NOT NULL FOREIGN KEY REFERENCES [Professor](professor_id),
  [course_number] INT NOT NULL FOREIGN KEY REFERENCES [Course](course_number),
  [ordering] INT,

  /* Constraints on composite primary key */
  PRIMARY KEY(professor_id, course_number)
)
