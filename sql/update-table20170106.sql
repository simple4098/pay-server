ALTER TABLE `pay_order_detail`
DROP INDEX `pay_order_account_id` ,
ADD INDEX `pay_order_account_id` (`pay_order_account_id`, `pay_account_id`, `order_no`, `pay_order`, `pay_no`, `pay_status`) USING BTREE ;