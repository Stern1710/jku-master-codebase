CREATE TABLE [stage].[StageJoin]
(
  [StageID] INT NOT NULL PRIMARY KEY IDENTITY,
  [join_type] VARCHAR(20) NOT NULL,
  [table_1] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TableInformation](table_id),
  [table_2] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TableInformation](table_id),
  [table_condition] varchar(MAX),
  [dwh_join_table_id] INT,
  [map_element_name] varchar(100),
  [map_condition] varchar(MAX) NOT NULL,
  [dwh_join_map_id] INT,
  [column_1_id] INT NOT NULL,
  [column_1_element_name] VARCHAR(100) NOT NULL,
  [column_2_id] INT NOT NULL,
  [column_2_element_name] VARCHAR(100) NOT NULL
)
