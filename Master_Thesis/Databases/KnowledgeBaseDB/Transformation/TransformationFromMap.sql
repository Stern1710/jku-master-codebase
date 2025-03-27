CREATE TABLE [kb].[TransformationFromMap]
(
  [column_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnInformation](column_id),
  [transformation_op_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TransformationOperation](transformation_op_id),
  [element_name] VARCHAR(100) NOT NULL,

  PRIMARY KEY (column_id, transformation_op_id)
)
