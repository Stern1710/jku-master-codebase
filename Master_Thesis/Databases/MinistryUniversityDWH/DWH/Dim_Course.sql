CREATE TABLE [dwh].[Dim_Course]
(
  [dim_course_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [university] VARCHAR(100),
  [institute] VARCHAR(100),
  [course_name] VARCHAR(100)
)
