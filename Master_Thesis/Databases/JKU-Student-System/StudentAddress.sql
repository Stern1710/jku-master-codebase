CREATE TABLE [dbo].[StudentAddress]
(
  [student_address_id] INT NOT NULL IDENTITY PRIMARY KEY,
  [street] VARCHAR(50) NOT NULL,
  [house_number] VARCHAR(50) NOT NULL,
  [additions] VARCHAR(50),
  [zip_code] INT NOT NULL,
  [city] VARCHAR(50) NOT NULL,
  [country] VARCHAR(50) NOT NULL
);