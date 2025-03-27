CREATE TABLE [kb].[TableSelectMap]
(
  [table_select_element_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[TableSelectElement](table_select_element_id),
  [column_id] INT NOT NULL FOREIGN KEY REFERENCES [kb].[ColumnInformation](column_id),
  [element_name] VARCHAR(100) NOT NULL,

  PRIMARY KEY (table_select_element_id, column_id)
)
