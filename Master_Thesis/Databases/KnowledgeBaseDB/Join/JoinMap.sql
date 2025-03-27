CREATE TABLE [kb].[JoinMap]
(
  [join_map_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [join_table_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[JoinTable](join_table_id),
  [element_name] VARCHAR(100),
  [condition] varchar(MAX) NOT NULL,
  [comment] VARCHAR(300) -- Space for a programmer to add a comment on why something was done
)
