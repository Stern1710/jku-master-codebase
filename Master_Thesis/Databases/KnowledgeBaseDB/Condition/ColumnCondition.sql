CREATE TABLE [kb].[ColumnCondition]
(
  [column_condition_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [column_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnInformation](column_id),
  [expression] varchar(MAX) NOT NULL,
  [comment] VARCHAR(300) -- Space for a programmer to add a comment on why something was done
)
