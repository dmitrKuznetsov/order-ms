INSERT INTO "order"(ID, ORDER_STATUS_ID, CUSTOMER_NAME, CUSTOMER_PHONE, CUSTOMER_COMMENT)
VALUES (NEXTVAL('order_seq') , 1, 'Ivanov I.I', '+7(952)-634-55-23', 'Pls. call before delivery');
INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME) VALUES (NEXTVAL('order_item_seq'), CURRVAL('order_seq') , 'Order Item #1');
INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME) VALUES (NEXTVAL('order_item_seq'), CURRVAL('order_seq') , 'Order Item #2');
INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME) VALUES (NEXTVAL('order_item_seq'), CURRVAL('order_seq') , 'Order Item #3');
