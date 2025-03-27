CREATE TABLE [kb].[TableInformation]
(
  [table_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [table_name] VARCHAR(200) NOT NULL,
  [schema] VARCHAR(100) NOT NULL,
  [database_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[DatabaseInformation](database_id),
  [table_select_id] INT FOREIGN KEY REFERENCES [kb].[TableSelect](table_select_id)
)
