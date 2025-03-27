CREATE TABLE [stage].[Stage_CountryCodeMap] (
  [id] INT NOT NULL IDENTITY PRIMARY KEY,
  [iso] CHAR(2) NOT NULL,
  [name] VARCHAR(80) NOT NULL,
  [nicename] VARCHAR(80) NOT NULL,
  [iso3] char(3) DEFAULT NULL,
);
