-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Апр 03 2020 г., 13:56
-- Версия сервера: 10.4.8-MariaDB
-- Версия PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `javaee_final`
--

-- --------------------------------------------------------

--
-- Структура таблицы `event`
--

CREATE TABLE `event` (
  `id` bigint(20) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `rooms_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `event`
--

INSERT INTO `event` (`id`, `color`, `end`, `start`, `text`, `resource_id`, `rooms_id`) VALUES
(33, '#f1c232', '2020-04-24 18:00:00', '2020-04-22 18:00:00', 'Booking id:9', 1, 1),
(34, NULL, '2020-03-25 18:00:00', '2020-03-24 18:00:00', 'Booking id:3', 1, 1),
(35, NULL, '2020-03-24 00:00:00', '2020-03-22 00:00:00', 'Booking id:1', 1, 1),
(36, NULL, '2020-03-29 00:00:00', '2020-03-27 00:00:00', 'Booking id:2', 1, 1),
(37, NULL, '2020-03-25 18:00:00', '2020-03-24 18:00:00', 'Booking id:12', 1, 3),
(38, NULL, '2020-04-01 18:00:00', '2020-03-28 18:00:00', 'Booking id:13', 1, 3),
(39, NULL, '2020-06-02 18:00:00', '2020-05-31 18:00:00', 'Booking id:8', 1, 5),
(40, NULL, '2020-04-22 18:00:00', '2020-04-15 18:00:00', 'Booking id:7', 1, 5),
(41, NULL, '2020-03-30 18:00:00', '2020-03-27 18:00:00', 'Booking id:4', 1, 5),
(42, NULL, '2020-04-01 18:00:00', '2020-03-31 18:00:00', 'Booking id:6', 1, 5),
(43, NULL, '2020-03-25 18:00:00', '2020-03-24 18:00:00', 'Booking id:5', 1, 5),
(44, NULL, '2020-04-08 18:00:00', '2020-04-04 18:00:00', 'Booking id:14', 1, 5),
(45, NULL, '2020-04-01 18:00:00', '2020-03-31 18:00:00', 'Booking by:customer email:eabilbay@gmail.comfullname:yERNAR ABILBAY', 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3),
(3);

-- --------------------------------------------------------

--
-- Структура таблицы `resource`
--

CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `resource`
--

INSERT INTO `resource` (`id`, `name`) VALUES
(1, 'Reserve info'),
(2, 'Tech-service.'),
(3, 'Additional');

-- --------------------------------------------------------

--
-- Структура таблицы `t_booking`
--

CREATE TABLE `t_booking` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_booking`
--

INSERT INTO `t_booking` (`id`, `created_at`, `deleted_at`, `updated_at`, `description`, `end_date`, `start_date`, `title`) VALUES
(1, NULL, NULL, NULL, 'this is some description', '2020-03-24 00:00:00', '2020-03-22 00:00:00', 'huiaitl'),
(2, NULL, NULL, NULL, 'Booking goood 2', '2020-03-29 00:00:00', '2020-03-27 00:00:00', 'asdasdasdasda'),
(3, '2020-03-25 13:30:27', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:yERNAR ABILBAY', '2020-03-25 18:00:00', '2020-03-24 18:00:00', 'This booking for room with id: 1'),
(4, '2020-03-25 13:47:35', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:Naruto uzumake', '2020-03-30 18:00:00', '2020-03-27 18:00:00', 'This booking for room with id: 5'),
(5, '2020-03-25 13:54:39', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:Omegalul odmen', '2020-03-25 18:00:00', '2020-03-24 18:00:00', 'This booking for room with id: 5'),
(6, '2020-03-25 13:56:30', NULL, NULL, 'customer email:orazaliev_d2@mail.rufullname:askhat orazalinov', '2020-04-01 18:00:00', '2020-03-31 18:00:00', 'This booking for room with id: 5'),
(7, '2020-03-25 14:42:24', NULL, NULL, 'customer email:eerzho@gmail.comfullname:zhanbo erkinbay', '2020-04-22 18:00:00', '2020-04-15 18:00:00', 'This booking for room with id: 5'),
(8, '2020-03-26 17:18:50', NULL, NULL, 'customer email:zhas1ko@bk.rufullname:Olzhas bermahanbet', '2020-06-02 18:00:00', '2020-05-31 18:00:00', 'This booking for room with id: 5'),
(9, '2020-03-31 08:54:57', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:yernar abilbay', '2020-04-24 18:00:00', '2020-04-22 18:00:00', 'This booking for room with id: 1'),
(12, '2020-03-31 09:29:10', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:yERNAR ABILBAY', '2020-03-25 18:00:00', '2020-03-24 18:00:00', 'This booking for room with id: 3'),
(13, '2020-03-31 09:29:58', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:yERNAR ABILBAY', '2020-04-01 18:00:00', '2020-03-28 18:00:00', 'This booking for room with id: 3'),
(14, '2020-04-03 09:09:39', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:yERNAR ABILBAY', '2020-04-08 18:00:00', '2020-04-04 18:00:00', 'This booking for room with id: 5'),
(15, '2020-04-03 11:20:27', NULL, NULL, 'customer email:eabilbay@gmail.comfullname:yERNAR ABILBAY', '2020-04-01 18:00:00', '2020-03-31 18:00:00', 'This booking for room with id: 1');

-- --------------------------------------------------------

--
-- Структура таблицы `t_category`
--

CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_category`
--

INSERT INTO `t_category` (`id`, `created_at`, `deleted_at`, `updated_at`, `title`, `description`, `picture`) VALUES
(1, NULL, NULL, NULL, 'Gold', 'Golden rooms include:\r\nAll inclusive furniture,apartment pool, filled freezer and food.', 'img/room/room-5.jpg'),
(2, NULL, NULL, NULL, 'Silver', 'Silver rooms include:\r\nAll inclusive furniture, Djakuzi.', 'img/room/room-4.jpg'),
(3, NULL, NULL, NULL, 'Bronze', 'Bronze rooms includes:\r\nAll inclusive furniture.', 'img/room/room-3.jpg');

-- --------------------------------------------------------

--
-- Структура таблицы `t_category_rooms`
--

CREATE TABLE `t_category_rooms` (
  `category_id` bigint(20) NOT NULL,
  `rooms_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_category_rooms`
--

INSERT INTO `t_category_rooms` (`category_id`, `rooms_id`) VALUES
(1, 4),
(1, 5),
(2, 1),
(3, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `t_comments`
--

CREATE TABLE `t_comments` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `email_person` varchar(255) DEFAULT NULL,
  `name_person` varchar(255) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `category` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `t_customers`
--

CREATE TABLE `t_customers` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `iin` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_customers`
--

INSERT INTO `t_customers` (`id`, `created_at`, `deleted_at`, `updated_at`, `email`, `full_name`, `iin`) VALUES
(2, '2020-03-25 13:30:27', NULL, NULL, 'eabilbay@gmail.com', 'yERNAR ABILBAY', 12345678),
(3, '2020-03-25 13:47:35', NULL, NULL, 'eabilbay@gmail.com', 'Naruto uzumake', 87654321),
(4, '2020-03-25 13:54:39', NULL, NULL, 'eabilbay@gmail.com', 'Omegalul odmen', 111111111),
(5, '2020-03-25 13:56:30', NULL, NULL, 'orazaliev_d2@mail.ru', 'askhat orazalinov', 12345678),
(6, '2020-03-25 14:42:24', NULL, NULL, 'eerzho@gmail.com', 'zhanbo erkinbay', 12312312312),
(7, '2020-03-26 17:18:50', NULL, NULL, 'zhas1ko@bk.ru', 'Olzhas bermahanbet', 12312312312),
(8, '2020-03-31 08:54:58', NULL, NULL, 'eabilbay@gmail.com', 'yernar abilbay', 12312312),
(9, '2020-03-31 09:09:34', NULL, NULL, 'eabilbay@gmail.com', 'yERNAR ABILBAY', 12312312),
(10, '2020-03-31 09:25:17', NULL, NULL, 'eabilbay@gmail.com', 'dfdfdsf', 12312312),
(11, '2020-03-31 09:29:10', NULL, NULL, 'eabilbay@gmail.com', 'yERNAR ABILBAY', 12345678),
(12, '2020-03-31 09:29:58', NULL, NULL, 'eabilbay@gmail.com', 'yERNAR ABILBAY', 12312321),
(13, '2020-04-03 09:09:40', NULL, NULL, 'eabilbay@gmail.com', 'yERNAR ABILBAY', 12312312321),
(14, '2020-04-03 11:20:27', NULL, NULL, 'eabilbay@gmail.com', 'yERNAR ABILBAY', 123123131);

-- --------------------------------------------------------

--
-- Структура таблицы `t_roles`
--

CREATE TABLE `t_roles` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_roles`
--

INSERT INTO `t_roles` (`id`, `created_at`, `deleted_at`, `updated_at`, `role`) VALUES
(1, '2020-03-18 00:00:00', NULL, NULL, 'ROLE_USER'),
(2, '2020-03-18 00:00:00', NULL, NULL, 'ROLE_ADMIN'),
(3, NULL, NULL, NULL, 'ROLE_MODERATOR');

-- --------------------------------------------------------

--
-- Структура таблицы `t_rooms`
--

CREATE TABLE `t_rooms` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_empty` bit(1) DEFAULT NULL,
  `is_reserved` bit(1) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `bed_info` varchar(255) DEFAULT NULL,
  `services` varchar(255) DEFAULT NULL,
  `cost` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_rooms`
--

INSERT INTO `t_rooms` (`id`, `created_at`, `deleted_at`, `updated_at`, `description`, `is_empty`, `is_reserved`, `number`, `picture`, `capacity`, `size`, `bed_info`, `services`, `cost`) VALUES
(1, NULL, NULL, '2020-04-03 11:20:27', 'Exclusive Silver room', b'1', b'1', 101, 'img/room/room-1.jpg', 2, 2, 'King bed', 'Wifi,Television,Djakuzi', 50),
(3, NULL, NULL, '2020-03-31 09:29:58', 'Exclusive Bronze room', b'1', b'0', 102, 'img/room/room-2.jpg\r\n', 1, 1, 'Middle-size', 'Wifi', 30),
(4, NULL, NULL, NULL, 'Exclusive Golden Room', b'1', b'0', 103, 'img/room/room-2.jpg', 3, 3, 'Kingdom-sized', 'Wi-fi, IoT, Djakuzi, Satellite television\r\nand a lot more.', 100),
(5, NULL, NULL, '2020-04-03 09:09:40', 'OmegaLul Room', b'1', b'0', 228, '	\r\nimg/room/room-1.jpg\r\n', 1, 1, 'Lord-size bed', 'Wi-fi, PlayStation, PC, freezer', 100);

-- --------------------------------------------------------

--
-- Структура таблицы `t_rooms_bookings`
--

CREATE TABLE `t_rooms_bookings` (
  `rooms_id` bigint(20) NOT NULL,
  `bookings_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_rooms_bookings`
--

INSERT INTO `t_rooms_bookings` (`rooms_id`, `bookings_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 9),
(1, 15),
(3, 12),
(3, 13),
(5, 4),
(5, 5),
(5, 6),
(5, 7),
(5, 8),
(5, 14);

-- --------------------------------------------------------

--
-- Структура таблицы `t_users`
--

CREATE TABLE `t_users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `iin` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_users`
--

INSERT INTO `t_users` (`id`, `created_at`, `deleted_at`, `updated_at`, `email`, `full_name`, `password`, `iin`) VALUES
(1, '2020-03-18 00:00:00', NULL, NULL, 'eabilbay@gmail.com', 'Yernar Abilbay', '123456', NULL),
(2, '2020-03-18 12:21:03', NULL, NULL, 'admin@mail.ru', 'Super Admin', 'admin007', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `t_users_roles`
--

CREATE TABLE `t_users_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  `customers_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_users_roles`
--

INSERT INTO `t_users_roles` (`users_id`, `roles_id`, `customers_id`) VALUES
(1, 2, 0),
(2, 1, 0);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKclx5xnhdf2y3l1g3ae6ygsjmf` (`resource_id`),
  ADD KEY `FKjlyc5y4rf0arc6yeade2i20im` (`rooms_id`);

--
-- Индексы таблицы `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_booking`
--
ALTER TABLE `t_booking`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_category`
--
ALTER TABLE `t_category`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_category_rooms`
--
ALTER TABLE `t_category_rooms`
  ADD PRIMARY KEY (`category_id`,`rooms_id`),
  ADD UNIQUE KEY `UK_8xaxsoa1io1ljcdx3u3me9boi` (`rooms_id`);

--
-- Индексы таблицы `t_comments`
--
ALTER TABLE `t_comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmifugn3fqi6yx0tbbam4qrs6x` (`category`);

--
-- Индексы таблицы `t_customers`
--
ALTER TABLE `t_customers`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_roles`
--
ALTER TABLE `t_roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_rooms`
--
ALTER TABLE `t_rooms`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_rooms_bookings`
--
ALTER TABLE `t_rooms_bookings`
  ADD PRIMARY KEY (`rooms_id`,`bookings_id`),
  ADD UNIQUE KEY `UK_6wlteejn5nosqmjdgq9bey2d2` (`bookings_id`);

--
-- Индексы таблицы `t_users`
--
ALTER TABLE `t_users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_users_roles`
--
ALTER TABLE `t_users_roles`
  ADD PRIMARY KEY (`users_id`,`roles_id`),
  ADD KEY `FK3iic7feebaqk9bauch70rdpy2` (`customers_id`),
  ADD KEY `FK9qf4n9htwf2hlfnqoucqmyeg9` (`roles_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `event`
--
ALTER TABLE `event`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT для таблицы `t_booking`
--
ALTER TABLE `t_booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `t_category`
--
ALTER TABLE `t_category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `t_comments`
--
ALTER TABLE `t_comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `t_customers`
--
ALTER TABLE `t_customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `t_roles`
--
ALTER TABLE `t_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `t_rooms`
--
ALTER TABLE `t_rooms`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `t_users`
--
ALTER TABLE `t_users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
