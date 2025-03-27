CREATE TABLE [dbo].[PruefungRegistrierung]
(
  [registrierung_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [student_id] INT NOT NULL FOREIGN KEY REFERENCES [Studierende](student_id),
  [pruefung_id] INT NOT NULL FOREIGN KEY REFERENCES [Pruefung](pruefung_id),
  [note] INT
);
