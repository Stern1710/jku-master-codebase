CREATE TABLE [kb].[ColumnConditionMap]
(
  [column_condition_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnCondition](column_condition_id),
  [column_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnInformation](column_id),
  [element_name] VARCHAR(100) NOT NULL,

  PRIMARY KEY (column_condition_id, column_id)
)
