CREATE TABLE [kb].[FillOrder]
(
  [fill_order_id] INT NOT NULL PRIMARY KEY IDENTITY,
  [table_name] VARCHAR(200),
  [table_schema] VARCHAR(100),
  [order_priority] INT NOT NULL
)
