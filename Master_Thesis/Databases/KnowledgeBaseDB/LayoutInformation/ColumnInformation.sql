CREATE TABLE [kb].[ColumnInformation]
(
  [column_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [table_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TableInformation](table_id),
  [column_name] VARCHAR(200) NOT NULL
)
