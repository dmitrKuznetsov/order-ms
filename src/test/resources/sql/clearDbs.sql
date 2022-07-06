DELETE FROM "order";
DELETE FROM ORDER_ITEM;

SELECT SETVAL('order_seq', 1000, true);
SELECT SETVAL('order_item_seq', 1000, true);