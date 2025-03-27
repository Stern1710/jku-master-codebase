-- This file contains SQL statements that will be executed after the build script.
-- Insert rows into table 'TableName' in schema '[dbo]'

DELETE FROM dbo.Registration;
DELETE FROM dbo.FieldOfStudy;
DELETE FROM dbo.Teaching;
DELETE FROM dbo.Student;
DELETE FROM dbo.StudentAddress;
DELETE FROM dbo.Professor;
DELETE FROM dbo.Course;
DELETE FROM dbo.StudyProgram;

DBCC CHECKIDENT ('[StudentAddress]', RESEED, 0);
DBCC CHECKIDENT ('[Professor]', RESEED, 0);
DBCC CHECKIDENT ('[StudyProgram]', RESEED, 0);
DBCC CHECKIDENT ('[Registration]', RESEED, 0);

INSERT INTO [dbo].[StudentAddress]
(
 [street], [house_number], [zip_code], [city], [country]
)
VALUES
('Freistaedter Strasse', '416a', 4040, 'Linz', 'Austria'),
('Linzer Straße', '56', 4240, 'Freistadt', 'Austria'),
('Ennser Straße', '33C', 4400, 'Steyr', 'Austria'),
('Almegg', '11', 4652, 'Steinerkirchen a.d. Traun', 'Austria'),
('Schwarzhöring', '14', 94575, 'Windorf', 'Germany'),
('Ipartelepi út', ' 8', 9330, 'Kapuvár', 'Ungarn'),
('Turracherhöhe', '178', 8864, 'Turracherhöhe', 'Austria'),
('Große Allee', '29', 34454, 'Bad Arolsen', 'Germany'),
('Grootmeestersstraat', '9', 5421, 'KK Gemert', 'Netherlands')

/* Get first ID of insert */
DECLARE @addressID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.StudentAddress) + 1;

INSERT INTO dbo.Student
(
  [matr_nr], [last_name], [first_name], [gender], [birthday], [matriculation_date], [mail_address], [email_ddress]
)
VALUES
(11812499, 'Sternbauer', 'Katharina', 0, '19971017', '20180801', @addressID, 'contact@sternbauer.eu'),
(11935234, 'Von Suttner', 'Bertha', 0, '18430609', '20200503', @addressID+1, 'bertha@vonsuttner.at'),
(11646823, 'Schrödinger', 'Erwin', 1, '18870812', '20160925', @addressID+2, 'physikgenie@gmail.com'),
(00823512, 'Pauli', 'Wolfgang', 1, '19001215', '20080223', @addressID+3, 'paulieffekt@yahoo.com'),
(11673057, 'Bosch', 'Carl', 1, '18740827', '20160413', @addressID+4, 'duengerfuerdiewelt@sorry.de'),
(11045734, 'Teller', 'Edward', 1, '19080115', '20101104', @addressID+5, 'bigboom@gov.us'),
(12158302, 'Coldwater', 'Ian', 2, '19800101', '20210328', @addressID+6, 'containerize@life.us'),
(09834523, 'Butler', 'Judith', 2, '19560224', '19980628', @addressID+7, 'morethantwogenders@accept.it'),
(00569406, 'Crone', 'Eveline', 0, '19751023', '20050912', @addressID+8, 'crone.e@leiden-uv.edu.nl')

DECLARE @studentID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Student) + 1;

INSERT INTO dbo.Professor (
  [name], [institute], [title]
)
VALUES
('Wobert Rille', 'Institute for Integrated Circuits', 'Prof. Dr.'),
('Adolf Adam', 'Angewandte Informatik', NULL),
('Anton Zeilinger', 'Quanten oder so', ' o. Univ.-Prof. Dr. phil. Dr. h. c. mult.'),
('Johannes Kofler', 'Institute for Integrated Circuits', 'Dr.')

DECLARE @profID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Professor) + 1;

INSERT INTO dbo.Course (
  [course_number], [course_name], [max_capacity], [ects], [location]
)
VALUES (355006, 'VL Digitale Schaltungen', 300, 3.0, 'HS 19'),
(365075, 'VL Machine Learning: Supervised Techniques', NULL, 3.0, 'HS 3'),
(366009, 'VO Rechnerarchitektur', 250, 4.5, 'HS 16'),
(338007, 'KV Debugging', 150, 3.0, 'Wechselnd'),
(353061, 'KV Introduction to Linux', 35, 1.5, 'P 215'),
(353006, 'VL Betriebssysteme', 400, 3.0, 'HS 1'),
(336058, 'KV Quantum Computing', 200, 3.0, 'HS 15'),
(322113, 'UE : Quantum Electronics and Optics', 25, 1.5, 'P 004')

INSERT INTO dbo.Teaching (
  [course_number], [professor_id], [ordering]
)
VALUES
(355006, @profID+0, 1),
(355006, @profID+3, 2),
(365075, @profID+3, NULL),
(366009, @profID+0, NULL),
(338007, @profID+1, 1),
(353061, @profID+1, 1),
(353061, @profID+3, 2),
(353006, @profID+1, NULL),
(336058, @profID+2, 1),
(336058, @profID+0, 2),
(322113, @profID+2, 1);

INSERT INTO dbo.StudyProgram (
  [name], [curr_link], [since], [faculty]
)
VALUES 
('Computer Science', 'https://studienhandbuch.jku.at/texte/975_11_BS_Informatik.pdf', '19700101', 'TNF'),
('Artificial Intelligence', 'https://studienhandbuch.jku.at/texte/848_3_BS_ArtificialIntelligence.pdf', '20190901', 'TNF'),
('Technische Physik', 'https://studienhandbuch.jku.at/texte/943_14_BS_TechPhysik.pdf', '19800401', 'TNF'),
('Soziologie', 'https://studienhandbuch.jku.at/texte/723_12_BS_Soziologie.pdf', '19850101', 'SoWi'),
('Rechtswissenschaften', 'https://studienhandbuch.jku.at/texte/1014_16_DS_Rechtswissenschaften.pdf', '19690115', 'JUS');
DECLARE @spID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.StudyProgram) + 1;


INSERT INTO dbo.FieldOfStudy (
  [matr_nr], [study_id], [is_primary]
)
VALUES
(11812499, @spID+0, DEFAULT),
(11935234, @spID+3, DEFAULT),
(11646823, @spID+2, DEFAULT),
(00823512, @spID+2, DEFAULT),
(00823512, @spID+1, 0),
(11673057, @spID+4, DEFAULT),
(11045734, @spID+2, DEFAULT),
(11045734, @spID+3, 0),
(12158302, @spID+0, 0),
(12158302, @spID+1, 1),
(09834523, @spID+3, 1),
(00569406, @spID+3, 1),
(00569406, @spID+4, 0);

INSERT INTO dbo.Registration (
  [student_id], [course_number] , [active], [grade], [semester], [study_id]
)
VALUES
(11812499, 355006, 1, '1', 'SS2023', @spID+0), (11812499, 365075, 1, NULL, 'SS2023', @spID+0), (11812499, 366009, 1, '1', 'WS2024', @spID+0), (11812499, 353061, 1, '3', 'WS2024', @spID+0), (11812499, 336058, 0, NULL, 'SS2024', @spID+0),
(11935234, 353061, 1, '2', 'SS2023', @spID+3), (11935234, 365075, 1, '4', 'WS2024', @spID+3),
(11646823, 336058, 1, '3', 'WS2024', @spID+2), (11646823, 322113, 1, '1', 'WS2024', @spID+2),
(00823512, 322113, 1, '1', 'SS2023', @spID+2), (00823512, 336058, 1, '1', 'SS2023', @spID+2), (00823512, 353006, 1, NULL, 'SS2024', @spID+1),
(11673057, 338007, 1, '4', 'SS2024', @spID+4), (11673057, 353006, 1, '1', 'WS2024', @spID+4), (11673057, 353061, 1, '2', 'WS2024', @spID+4), (11673057, 365075, 1, '4', 'SS2024', @spID+4), (11673057, 355006, 1, '3', 'SS2024', @spID+4),
(11045734, 365075, 1, '5', 'WS2024', @spID+2), (11045734, 322113, 1, '2', 'WS2024', @spID+2), (11045734, 353061, 0, NULL, 'WS2023', @spID+2), (11045734, 366009, 1, NULL, 'WS2023', @spID+3), 
(12158302, 355006, 1, '1', 'SS2024', @spID+0), (12158302, 366009, 1, '3', 'SS2024', @spID+0), (12158302, 338007, 1, '1', 'WS2024', @spID+0), (12158302, 353061, 1, '1', 'WS2023', @spID+0), (12158302, 353006, 1, '2', 'WS2023', @spID+1), (12158302, 336058, 0, NULL, 'SS2024', @spID+1),
(09834523, 365075, 0, NULL, 'SS2023', @spID+3), (09834523, 322113, 0, '4', 'SS2024', @spID+3), (09834523, 338007, 1, '1', 'WS2024', @spID+3),
(00569406, 355006, 1, '2', 'SS2023', @spID+3),(00569406, 365075, 1, '3', 'WS2023', @spID+3),(00569406, 366009, 1, '1', 'SS2023', @spID+3),(00569406, 338007, 0, NULL, 'SS2023', @spID+4),(00569406, 353061, 1, '3', 'SS2024', @spID+4),(00569406, 353006, 1, '4', 'SS2024', @spID+4) ,(00569406, 336058, 1, '2', 'WS2023', @spID+4),(00569406, 322113, 1, '1', 'WS2023', @spID+4)
