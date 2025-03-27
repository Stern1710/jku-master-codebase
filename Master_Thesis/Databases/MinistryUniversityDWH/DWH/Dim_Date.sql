CREATE TABLE [dwh].[Dim_Date]
(
  [dim_date_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [year] VARCHAR(100) NOT NULL,
  [semester] VARCHAR(100) NOT NULL,
  [month] VARCHAR(100) NOT NULL
)
