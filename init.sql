CREATE USER 'application_user'@'%' IDENTIFIED BY 'application_user';
GRANT ALL PRIVILEGES ON architecture_pattern.* TO 'application_user'@'%';

CREATE TABLE `user_account`
(
    `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `email`            VARCHAR(255) NOT NULL,
    `first_name`       VARCHAR(255) NOT NULL,
    `last_name`        VARCHAR(255) NOT NULL,
    `password`         VARCHAR(255) NOT NULL,
    `last_modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_at`       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8 ENGINE = InnoDB;

CREATE TABLE `active_order`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `price`    decimal    NOT NULL,
    `quantity` decimal    NOT NULL,
    `user_id`  bigint(20),
    `last_modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_at`       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES user_account (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;