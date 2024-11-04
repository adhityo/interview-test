-- interview.product definition

CREATE TABLE `interview`.`product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unique_code` varchar(50) DEFAULT NULL,
  `product_name` varchar(150) DEFAULT NULL,
  `product_sku` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `inventory_total` double DEFAULT NULL,
  `booked_inventory_total` double DEFAULT NULL,
  `returned_inventory_total` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `deleted_time` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(50) DEFAULT NULL,
  `deleted_status` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;