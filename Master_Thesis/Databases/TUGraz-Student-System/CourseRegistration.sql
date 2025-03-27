CREATE TABLE [dbo].[CourseRegistration]
(
  [course_registration_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [student_id] INT NOT NULL FOREIGN KEY REFERENCES [Student](student_id),
  [course_id] INT NOT NULL FOREIGN KEY REFERENCES [Course](course_id)
);
