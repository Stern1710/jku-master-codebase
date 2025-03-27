CREATE TABLE [kb].[TransformationOperation]
(
  [transformation_op_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [column_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnInformation](column_id),
  [source_database] INT NOT NULL FOREIGN KEY REFERENCES [kb].[DatabaseInformation](database_id),
  [expression] varchar(MAX) NOT NULL,
  [comment] VARCHAR(300) -- Space for a programmer to add a comment on why something was done
)
