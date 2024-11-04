
CREATE TABLE `interview-auth`.`tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unique_code` varchar(50) DEFAULT NULL,
  `tenant_name` varchar(150) DEFAULT NULL,
  `tenant_target_route` varchar(200) DEFAULT NULL,
  `current_token` varchar(255) DEFAULT NULL,
  `current_token_expire_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` varchar(50) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `deleted_time` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(50) DEFAULT NULL,
  `deleted_status` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;