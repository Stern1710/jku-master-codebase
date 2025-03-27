CREATE TABLE [kb].[TableSelectElement]
(
  [table_select_element_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [table_select_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TableSelect](table_select_id),
  [expression] varchar(MAX) NOT NULL,
  [as_name] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnInformation](column_id),
  [comment] VARCHAR(300) -- Space for a programmer to add a comment on why something was done
)
