CREATE TABLE [dbo].[Lehrveranstaltung]
(
  [lva_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [semester_id] INT NOT NULL FOREIGN KEY REFERENCES [Semester](semester_id),
  [voraussetzung_lva] INT FOREIGN KEY REFERENCES [Lehrveranstaltung](lva_id),
  [name] VARCHAR(100) NOT NULL,
  [ects] FLOAT NOT NULL,
  [professoren] VARCHAR(300) NOT NULL,
  [institut] VARCHAR(200) NOT NULL,
  [ort] VARCHAR(100) NOT NULL
);
