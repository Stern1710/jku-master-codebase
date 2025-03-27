CREATE TABLE [stage].[Stage_DWH_Data]
(
  [stage_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [university] VARCHAR(100),
  [institute] VARCHAR(100),
  [course_name] VARCHAR(100),
  [course_dwh_id] INT,
  [year] VARCHAR(100) NOT NULL,
  [semester] VARCHAR(100) NOT NULL,
  [month] VARCHAR(100) NOT NULL,
  [date_dwh_id] INT,
  [gender] VARCHAR(100) NOT NULL,
  [country] VARCHAR(100) NOT NULL,
  [city] VARCHAR(100) NOT NULL,
  [student_dwh_id] INT,
  [faculty] VARCHAR(100) NOT NULL,
  [study_program] VARCHAR(100) NOT NULL,
  [study_dwh_id] INT,
  [grade] INT NOT NULL,
  [success] BIT NOT NULL,
  [ects] FLOAT NOT NULL
)
