CREATE USER 'application_user'@'%' IDENTIFIED BY 'application_user';
GRANT ALL PRIVILEGES ON architecture_pattern.* TO 'application_user'@'%';

CREATE TABLE `user_account`
(
    `id`         bigint(20)   NOT NULL AUTO_INCREMENT,
    `email`      varchar(255) NOT NULL,
    `first_name` varchar(255) NOT NULL,
    `last_name`  varchar(255) NOT NULL,
    `password`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;