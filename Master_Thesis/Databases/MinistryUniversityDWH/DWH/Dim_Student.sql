CREATE TABLE [dwh].[Dim_Student]
(
  [dim_student_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [gender] VARCHAR(100) NOT NULL,
  [country] VARCHAR(100) NOT NULL,
  [city] VARCHAR(100) NOT NULL
)
