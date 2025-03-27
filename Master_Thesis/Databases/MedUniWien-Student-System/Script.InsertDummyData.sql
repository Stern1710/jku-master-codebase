DELETE FROM dbo.PruefungRegistrierung;
DELETE FROM dbo.Pruefung;
DELETE FROM dbo.Lehrveranstaltung;
DELETE FROM dbo.Studierende;
DELETE FROM dbo.Semester;
DELETE FROM dbo.Studium;

DBCC CHECKIDENT ('[PruefungRegistrierung]', RESEED, 0);
DBCC CHECKIDENT ('[Semester]', RESEED, 0);
DBCC CHECKIDENT ('[Lehrveranstaltung]', RESEED, 0);
DBCC CHECKIDENT ('[Pruefung]', RESEED, 0);
DBCC CHECKIDENT ('[Studierende]', RESEED, 0);
DBCC CHECKIDENT ('[Studium]', RESEED, 0);

INSERT INTO [dbo].[Studium]
([studium_name], [fakultaet])
VALUES
('Bachelor Humanmedizin', 'Medizinische Fakultät'), ('Master Humanmedizin', 'Medizinische Fakultät')
DECLARE @studID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Studium) + 1;

INSERT INTO [dbo].[Semester]
( [semesternummer], [studium_id] )
VALUES
(1, @studID+0),(2, @studID+0),(3, @studID+0),(4, @studID+0),(5, @studID+0),(6, @studID+0),
(1, @studID+1),(2, @studID+1),(3, @studID+1),(4, @studID+1),(5, @studID+1),(6, @studID+1);
DECLARE @semID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Semester) + 1;


INSERT INTO dbo.Lehrveranstaltung
([semester_id], [name], [ects], [professoren], [ort], [institut])
VALUES
(@semID+0, 'Einführung in Humanmedizin', 6, 'DDr. Gertrude Menger', 'Halle 1', 'Allgemeinmedizin'),
(@semID+0, 'Innere Medizin 1', 4.5, 'Prof. Dr. Hans Arzt', 'MED Saal 1', 'Innere Medizin'),
(@semID+1, 'Ethik', 3.0, 'Mag. Eduart Manner', 'HS 10', 'Gesellschaft und Gesundheit'),
(@semID+1, 'Einführung Fachbereiche', 1.5, 'TBD', 'Halle 1', 'Allgemeinmedizin'),
(@semID+2, 'Innere Medizin 2', 3.0, 'Prof. Dr. Bert Doc', 'MED Saal 1', 'Innere Medizin'),
(@semID+3, 'Gendermedizin 1', 4.0, 'Dr. Prof. Birgit Hammer', 'Med 2', 'Gesellschaft und Gesundheit'),
(@semID+4, 'Innere Medizin 3', 7.0, 'Prof. Dr. Kurz Meister', 'MED Saal 2', 'Innere Medizin'),
(@semID+4, 'Gendermedizin 2', 4.0, 'Dr. Manuel Inzuin', 'LF 501', 'Gesellschaft und Gesundheit'),
(@semID+5, 'Verdauung', 9.0, 'Ute Bauer, PhD', 'ZML 110', 'Innere Medizin')

DECLARE @lvaID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Lehrveranstaltung) + 1;

UPDATE [dbo].[Lehrveranstaltung] SET [Voraussetzung_LVA] = @lvaID+1 WHERE LVA_ID = @lvaID+4;
UPDATE [dbo].[Lehrveranstaltung] SET [Voraussetzung_LVA] = @lvaID+4 WHERE LVA_ID = @lvaID+6;
UPDATE [dbo].[Lehrveranstaltung] SET [Voraussetzung_LVA] = @lvaID+5 WHERE LVA_ID = @lvaID+7;


INSERT INTO [dbo].[Pruefung]
([datum], [uhrzeit], [lva_id])
VALUES
('20240115', '1315', @lvaID+0),('20240120', '0830', @lvaID+0),
('20240201', '1015', @lvaID+1),('20240202', '1045', @lvaID+1),('20240203', '1030', @lvaID+1),
('20240601', '1015', @lvaID+2),
('20240504', '1335', @lvaID+3),('20240507', '0730', @lvaID+3),('20240520', '0730', @lvaID+3),
('20231216', 'TBD', @lvaID+4),('20231221', '1450', @lvaID+4),
('20240702', '0915', @lvaID+5), ('20240801', '1130', @lvaID+5),
('20231105', '1730', @lvaID+6),
('20240110', '0915', @lvaID+7),('20240110', '1015', @lvaID+7),('20240110', '1115', @lvaID+7),
('20240523', '0915', @lvaID+8),('20240524', '0915', @lvaID+8),('20240525', '0915', @lvaID+8),('20240526', '0915', @lvaID+8)
DECLARE @pID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Pruefung) + 1;


INSERT INTO [dbo].[Studierende]
([matr_nr], [vorname], [nachname], [geschlecht], [strasse], [hausnummer], [zusatz], [plz], [stadt], [land], [med_at_punkte], [geburtstag], [matrikulierung], [aktuelles_semester])
VALUES
(12312345, 'Markus', 'Nachhuber', 'Männlich', 'Mengerstraße', '23', NULL, 4040, 'Linz', 'AT', 90, '20000101', '20230901', @semID+0),
(12397578, 'Janine', 'Mötzler', 'Weiblich', 'Freistädter Straße', '467', '1/4', 1020, 'Wien', 'AT', 76, '20020915', '20230901', @semID+0),
(12370367, 'Sam', 'Krisenbauer', 'Divers', 'Linzer Allee', '54', '5', 1010, 'Wien', 'AT', 87, '20010925', '20230901', @semID+1),
(12248394, 'Karsten', 'Trist', 'Männlich', 'Berlinerstraße', '43', NULL, 10115, 'Berlin', 'DE', 98, '19951107', '20220901', @semID+2),
(12283094, 'Kristen', 'Schlaghuber', 'Divers', 'Halleinweg', '2', 'TOP 7', 3390, 'Melk', 'AT', 81, '20030329', '20220901', @semID+3), 
(12115929, 'Thomas', 'Benton', 'Männlich', 'Hans-Thomann-Strasse', '1', NULL, 96138, 'Burgebrach', 'DE', 92, '19990624', '20210901', @semID+4), 
(12111209, 'Christine', 'Staudinger', 'Weiblich', 'Feldgasse', '6', NULL, 7071, 'Rust', 'AT', 73, '19980214', '20210901', @semID+4),
(12178656, 'Carmen', 'Haslinger', 'Weiblich', 'Bahnhofstraße', '67', '1', 4240, 'Freistadt', 'AT', 94, '19961203', '20210901', @semID+5) /* +7 */
DECLARE @studentID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Studierende) + 1;


INSERT INTO [dbo].[PruefungRegistrierung]
([student_id], [pruefung_id], [note] )
VALUES
(@studentID+0, @pID+0, NULL),(@studentID+0, @pID+1, 2),(@studentID+0, @pID+2, 1),
(@studentID+1, @pID+1, 3), (@studentID+1, @pID+2, 5), (@studentID+1, @pID+4, 1),
(@studentID+2, @pID+5, 3), (@studentID+2, @pID+6, 4), (@studentID+2, @pID+7, NULL), (@studentID+2, @pID+8, 1),
(@studentID+3, @pID+9, 1),
(@studentID+4, @pID+11, 5), (@studentID+4, @pID+12, 1),
(@studentID+5, @pID+13, 3), (@studentID+5, @pID+14, 5),(@studentID+5, @pID+15, NULL), (@studentID+5, @pID+16, 4),
(@studentID+6, @pID+13, 4), (@studentID+6, @pID+15, 1),
(@studentID+7, @pID+17, NULL), (@studentID+7, @pID+19, 4), (@studentID+7, @pID+20, 2)