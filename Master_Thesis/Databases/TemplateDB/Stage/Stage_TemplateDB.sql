CREATE TABLE [stage].[Stage_TemplateDB]
(
  [stage_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [name_nk] VARCHAR(100) NOT NULL,
  [dwh_university_id] INT,
  
  [faculty] VARCHAR(100) NOT NULL,
  [study_program] VARCHAR(100) NOT NULL,
  [dwh_study_sk] INT,

  [gender] VARCHAR(100) NOT NULL,
  [country] VARCHAR(100) NOT NULL,
  [city] VARCHAR(100) NOT NULL,
  [dwh_student_sk] INT,

  [grade] INT NOT NULL,
  [year] VARCHAR(100) NOT NULL,
  [semester] VARCHAR(100) NOT NULL,
  [month] VARCHAR(100) NOT NULL,
  [dwh_exam_sk] INT,
  
  [institute] VARCHAR(100),
  [course_name] VARCHAR(100),
  [ects] FLOAT NOT NULL,
  [dwh_course_sk] INT,
)
