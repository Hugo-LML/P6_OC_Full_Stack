CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(40) NOT NULL UNIQUE,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `ARTICLES` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL UNIQUE,
  `user_username` VARCHAR(255),
  `theme_id` INT,
  `content` TEXT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `THEMES` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL UNIQUE,
  `description` TEXT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `COMMENTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_username` VARCHAR(255),
  `article_id` INT,
  `content` TEXT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `USER_THEMES` (
  `user_id` INT,
  `theme_id` INT
);

ALTER TABLE `ARTICLES` ADD FOREIGN KEY (`user_username`) REFERENCES `USERS` (`username`);
ALTER TABLE `ARTICLES` ADD FOREIGN KEY (`theme_id`) REFERENCES `THEMES` (`id`);

ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`user_username`) REFERENCES `USERS` (`username`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`article_id`) REFERENCES `ARTICLES` (`id`);

ALTER TABLE `USER_THEMES` ADD PRIMARY KEY (`user_id`, `theme_id`);
ALTER TABLE `USER_THEMES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `USER_THEMES` ADD FOREIGN KEY (`theme_id`) REFERENCES `THEMES` (`id`);

INSERT INTO USERS (username, email, password)
VALUES (
  'Hugo_LML',
  'test@gmail.com',
  '$2a$10$qfV/l8tY3yvE4rvV/LSNbeMpZ596fdDcSOXy5s5TkoK3LxkAe1QZC'
);

INSERT INTO THEMES (title, description)
VALUES (
  'JavaScript',
  'JavaScript est un langage de programmation de scripts principalement employé dans les pages web interactives et à ce titre est une partie essentielle des applications web. Avec les langages HTML et CSS, JavaScript est au coeur des langages utilisés par les développeurs web.'
);

INSERT INTO ARTICLES (title, user_username, theme_id, content)
VALUES (
  'Débutez avec React',
  'Hugo_LML',
  1,
  "React peut changer votre façon de concevoir les designs que vous regardez et les applications que vous créez. Lorsque vous construisez une interface utilisateur avec React, vous la décomposez d'abord en éléments appelés composants. Ensuite, vous décrirez les différents états visuels de chacun de vos composants. Enfin, vous connecterez vos composants ensemble pour que les données circulent à travers eux. Dans ce tutoriel, nous vous guiderons à travers le processus de réflexion de la construction d'un tableau de données de produits consultables avec React."
);

INSERT INTO COMMENTS (user_username, article_id, content)
VALUES (
  'Hugo_LML',
  1,
  "D'autres articles sont en cours d'écriture !"
);

INSERT INTO USER_THEMES (user_id, theme_id)
VALUES (
  1,
  1
);