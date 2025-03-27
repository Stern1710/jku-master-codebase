CREATE TABLE [kb].[JoinColumn]
(
  [join_column_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [join_map_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[JoinMap](join_map_id),
  [column_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnInformation](column_id),
  [element_name] VARCHAR(100) NOT NULL
)
