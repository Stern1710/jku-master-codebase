CREATE TABLE [dbo].[Studierende]
(
  [student_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [matr_nr] VARCHAR(12) NOT NULL,
  [vorname] VARCHAR(200) NOT NULL,
  [nachname] VARCHAR(100) NOT NULL,
  [geschlecht] VARCHAR(30) NOT NULL,
  [strasse] VARCHAR(50) NOT NULL,
  [hausnummer] VARCHAR(10) NOT NULL,
  [zusatz] VARCHAR(10),
  [plz] INT NOT NULL,
  [stadt] VARCHAR(100) NOT NULL,
  [land] VARCHAR(50),
  [med_at_punkte] INT,
  [geburtstag] VARCHAR(12) NOT NULL,
  [matrikulierung] VARCHAR(12) NOT NULL,
  [aktuelles_semester] INT FOREIGN KEY REFERENCES [Semester](semester_id)
);
