CREATE TABLE [kb].[JoinTable]
(
  [join_table_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [join_type] VARCHAR(20) NOT NULL,
  [table_1] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TableInformation](table_id),
  [table_2] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TableInformation](table_id),
  [condition] varchar(MAX), -- Optional as with 1 JoinMap, everything is quite clear
  [comment] VARCHAR(300) -- Space for a programmer to add a comment on why something was done
)
