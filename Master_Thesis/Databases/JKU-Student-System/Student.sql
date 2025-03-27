CREATE TABLE [dbo].[Student]
(
  [matr_nr] INT NOT NULL PRIMARY KEY,
  [last_name] VARCHAR(100) NOT NULL,
  [middle_name] VARCHAR(200),
  [first_name] VARCHAR(100) NOT NULL,
  [gender] INT NOT NULL,
  [birthday] DATE,
  [matriculation_date] DATE NOT NULL,
  [mail_address] INT NOT NULL FOREIGN KEY REFERENCES [dbo].[StudentAddress](student_address_id),
  [email_ddress] VARCHAR(50) NOT NULL
);