CREATE TABLE [dbo].[Pruefung]
(
  [pruefung_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [datum] VARCHAR(12) NOT NULL,
  [uhrzeit] VARCHAR(50) NOT NULL,
  [lva_id] INT NOT NULL FOREIGN KEY REFERENCES [Lehrveranstaltung](lva_id)
);
