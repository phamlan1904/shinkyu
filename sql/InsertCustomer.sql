/**
 * 会員テーブル　テストデータ
 * Author:  yoshinobu
 * Created: 2018/10/02
 */
DELETE FROM CUSTOMER;
INSERT INTO CUSTOMER (TELNO, "NAME", ADDRESS, ORDERCOUNT, SERVICECOUNT) VALUES ('0311111111', 'ヤマダタロウ', '新宿区AAA', 2, 0);
INSERT INTO CUSTOMER (TELNO, "NAME", ADDRESS, ORDERCOUNT, SERVICECOUNT) VALUES ('0322222222', 'スズキヨシノブ', '新宿区BBB', 5, 1);
INSERT INTO CUSTOMER (TELNO, "NAME", ADDRESS, ORDERCOUNT, SERVICECOUNT) VALUES ('0344444444', 'セキカワリョウ', '新宿区DDD', 1, 0);
INSERT INTO CUSTOMER (TELNO, "NAME", ADDRESS, ORDERCOUNT, SERVICECOUNT) VALUES ('0355555555', 'ファムラン', '新宿区EEE', 10, 2);