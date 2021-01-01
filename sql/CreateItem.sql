
DROP TABLE ITEM;        



CREATE TABLE ITEM (
 ITEMNO         INTEGER generated always as identity(start with 1, increment by 1),
 ITEMNAME       CHAR(30),
 PRICE           INTEGER,
 CONSTRAINT PK_ITEM PRIMARY KEY (ITEMNO)
);
