DELETE FROM dbo.ExamRegistration;
DELETE FROM dbo.CourseRegistration;
DELETE FROM dbo.Exam;
DELETE FROM dbo.Course;
DELETE FROM dbo.Student;
DELETE FROM dbo.StudyField;
DELETE FROM dbo.Faculty;

DBCC CHECKIDENT ('[ExamRegistration]', RESEED, 0);
DBCC CHECKIDENT ('[CourseRegistration]', RESEED, 0);
DBCC CHECKIDENT ('[Student]', RESEED, 0);
DBCC CHECKIDENT ('[StudyField]', RESEED, 0);
DBCC CHECKIDENT ('[Faculty]', RESEED, 0);
DBCC CHECKIDENT ('[Exam]', RESEED, 0);

INSERT INTO [dbo].[Faculty]
([name], [head_professor])
VALUES
('Informatik', 'Prof. Dr. Heinz Rummenige'),
('Halbleiter', NULL),
('Artificial Intelligence', 'DDr. Christine Cramesworth, PhD')
DECLARE @facultyID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Faculty) + 1;


INSERT INTO [dbo].[StudyField]
([study_nr], [name], [since_date], [faculty_id])
VALUES
(035, 'Computer Science', 315532800, @facultyID+0),
(011, 'Hardware Engineering', 1283299200, @facultyID+1),
(039, 'Artificial Intelligence', 1654041600, @facultyID+2)
DECLARE @studyID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.StudyField) + 1;


INSERT INTO [dbo].[Course]
( [course_id], [name], [ects], [max_people], [professor1], [professor2], [other_professor], [institute])
VALUES
(628, 'Einführung in die Informatik', 3.0, 500, 'Mario Lackner', NULL, NULL, 'Softwaresysteme'),
(626, 'Programmierung 1', 4.5, 300, 'Helga Schweinseder', 'Mario Kramer', NULL, 'Softwaresysteme'),
(163, 'Basics of Silicon', 1.5, 60, 'Moritz Plattner', 'Johannes Wehner', 'Daniel Figiel', 'Hardwaresysteme'),
(102, 'VHDL Expanded', 6.0, 40, 'Erik Borschinsky', NULL, NULL, 'Hardwaresysteme'),
(914, 'Ethics in AI', 3.0, 320, 'Henrik Engelsbert', NULL, NULL, 'Roboterpsychologie'),
(973, 'Python 1', 4.0, 500, 'Philipp Pfab', 'Martin Aschacher', NULL, 'Softwaresysteme')


INSERT INTO [dbo].[Student]
([matr_nr], [full_name], [field_of_study], [gender], [display_name], [full_address], [birthday], [matr_date])
VALUES
(12370496, 'Gustav Maria Hobelmeier', @studyID+0, 0, 'GHobelmeier', 'Am Tivoli 1, 8700, Leoben, AT', 
DATEDIFF_BIG(s, '1970-01-01', '2004-06-02'), DATEDIFF_BIG(s, '1970-01-01', '2023-08-31')),
(12279430, 'Melanie Katzmannstorfer', @studyID+0, 1, 'MKMT', 'u. Hauptstraße 70, 7422, Riedlingsdorf, AT', 
DATEDIFF_BIG(s, '1970-01-01', '2002-11-11'), DATEDIFF_BIG(s, '1970-01-01', '2022-07-05')),
(11845904, 'Ary Holmandinger', @studyID+1, NULL, 'AryMandi', 'Bahnhofstraße 16, 9800, Spittal an der Drau, AT', 
DATEDIFF_BIG(s, '1970-01-01', '1998-07-17'), DATEDIFF_BIG(s, '1970-01-01', '2018-10-01')),
(11950783, 'Hubert Zcancky', @studyID+1, 0, 'HZancky', 'Gołębia 1, 61-779, Poznań, PL', 
DATEDIFF_BIG(s, '1970-01-01', '2000-02-04'), DATEDIFF_BIG(s, '1970-01-01', '2019-08-30')),
(11687568, 'Kurt Gratzinger', @studyID+2, 1, 'KurtiG', 'Roseggerstraße 20, 4020, Linz, AT', 
DATEDIFF_BIG(s, '1970-01-01', '1996-05-12'), DATEDIFF_BIG(s, '1970-01-01', '2016-02-29')),
(10583495, 'Karla Wenzigner', @studyID+2, 1, 'WenzKa', 'Beda Weber-Gasse 1a, 9900, Lienz, AT', 
DATEDIFF_BIG(s, '1970-01-01', '1994-03-31'), DATEDIFF_BIG(s, '1970-01-01', '2010-10-04'))
DECLARE @studentID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Student) + 1;


INSERT INTO [dbo].[Exam]
( [course_id], [time_from], [time_to], [room] )
VALUES
(628, DATEDIFF_BIG(s, '1970-01-01', '2024-02-01 10:30:00'), DATEDIFF_BIG(s, '1970-01-01', '2024-02-01 12:30:00'), 'LH 2'), -- +0
(628, DATEDIFF_BIG(s, '1970-01-01', '2024-02-27 08:30:00'), DATEDIFF_BIG(s, '1970-01-01', '2024-02-27 10:30:00'), 'LH 3'),
(626, DATEDIFF_BIG(s, '1970-01-01', '2023-07-27 08:30:00'), DATEDIFF_BIG(s, '1970-01-01', '2023-07-27 09:15:00'), 'SE 430'),
(626, DATEDIFF_BIG(s, '1970-01-01', '2023-09-04 12:15:00'), DATEDIFF_BIG(s, '1970-01-01', '2023-09-04 13:00:00'), 'UG 04'),

(163, DATEDIFF_BIG(s, '1970-01-01', '2023-01-31 15:00:00'), DATEDIFF_BIG(s, '1970-01-01', '2023-01-31 16:30:00'), 'LS 23'), -- +4
(102, DATEDIFF_BIG(s, '1970-01-01', '2024-05-14 15:00:00'), DATEDIFF_BIG(s, '1970-01-01', '2024-05-14 16:30:00'), 'LH 1'),
(102, DATEDIFF_BIG(s, '1970-01-01', '2024-06-01 11:45:00'), DATEDIFF_BIG(s, '1970-01-01', '2024-06-01 13:15:00'), 'LH 1'),

(914, DATEDIFF_BIG(s, '1970-01-01', '2022-02-04 09:30:00'), DATEDIFF_BIG(s, '1970-01-01', '2022-02-04 12:00:00'), 'SE 120'), -- +7
(914, DATEDIFF_BIG(s, '1970-01-01', '2022-03-15 13:30:00'), DATEDIFF_BIG(s, '1970-01-01', '2022-03-15 16:00:00'), 'SE 541'),
(973, DATEDIFF_BIG(s, '1970-01-01', '2023-06-02 10:15:00'), DATEDIFF_BIG(s, '1970-01-01', '2024-06-01 11:15:00'), 'LH 2'),
(973, DATEDIFF_BIG(s, '1970-01-01', '2023-06-04 10:15:00'), DATEDIFF_BIG(s, '1970-01-01', '2024-06-04 11:15:00'), 'LH 2'),
(973, DATEDIFF_BIG(s, '1970-01-01', '2023-06-06 10:45:00'), DATEDIFF_BIG(s, '1970-01-01', '2024-06-06 11:45:00'), 'LH 2') -- +11
DECLARE @examID int = SCOPE_IDENTITY() - (SELECT COUNT(*) FROM dbo.Exam) + 1;


-- Insert rows into table 'TableName' in schema '[dbo]'
INSERT INTO [dbo].[CourseRegistration]
( -- Columns to insert data into
 [student_id], [course_id]
)
VALUES
(@studentID+0, 628), (@studentID+0, 626), (@studentID+0, 163), (@studentID+0, 973),
(@studentID+1, 628), (@studentID+1, 626), (@studentID+1, 102),
(@studentID+2, 163), (@studentID+2, 102), (@studentID+2, 626), (@studentID+2, 914), (@studentID+2, 973),
(@studentID+3, 163), (@studentID+3, 102), (@studentID+3, 628),
(@studentID+4, 914), (@studentID+4, 973), (@studentID+4, 628), (@studentID+4, 626),
(@studentID+5, 914), (@studentID+5, 973)


INSERT INTO [dbo].[ExamRegistration]
( -- Columns to insert data into
 [student_id], [exam_id], [grade]
)
VALUES
(@studentID+0, @examID+0, 5), (@studentID+0, @examID+1, 1), (@studentID+0, @examID+3, 2), (@studentID+0, @examID+4, NULL), (@studentID+0, @examID+10, 3),
(@studentID+1, @examID+0, 2), (@studentID+1, @examID+2, 5), (@studentID+1, @examID+3, 4), (@studentID+1, @examID+6, 2),
(@studentID+2, @examID+4, 1), (@studentID+2, @examID+5, 2), (@studentID+2, @examID+6, 1), (@studentID+2, @examID+3, 2), (@studentID+2, @examID+8, 3), (@studentID+2, @examID+11, 1),
(@studentID+3, @examID+4, 4), (@studentID+3, @examID+6, 2),
(@studentID+4, @examID+7, 3), (@studentID+4, @examID+9, NULL), (@studentID+4, @examID+10, 5), (@studentID+4, @examID+11, 2), (@studentID+4, @examID+1, 4), (@studentID+4, @examID+2, 1),
(@studentID+5, @examID+7, NULL), (@studentID+5, @examID+8, 4), (@studentID+5, @examID+9, 3)