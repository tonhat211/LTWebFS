-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 18, 2023 lúc 10:41 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ltweb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `branches`
--

CREATE TABLE `branches` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `address` text NOT NULL,
  `phone` varchar(250) NOT NULL,
  `available` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `branches`
--

INSERT INTO `branches` (`id`, `name`, `address`, `phone`, `available`) VALUES
(1, 'chi nhanh hcm', 'thu duc - ho chi minh ', '0444444', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brands`
--

CREATE TABLE `brands` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `available` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `brands`
--

INSERT INTO `brands` (`id`, `name`, `available`) VALUES
(1, 'Stryker', 1),
(2, 'Philips', 1),
(3, 'Siemens', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `de_orders`
--

CREATE TABLE `de_orders` (
  `ordID` int(11) NOT NULL,
  `imei` int(11) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `home_contents`
--

CREATE TABLE `home_contents` (
  `id` int(11) NOT NULL,
  `indx` int(11) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `home_contents`
--

INSERT INTO `home_contents` (`id`, `indx`, `description`) VALUES
(1, 1, NULL),
(2, 2, NULL),
(3, 3, NULL),
(4, 4, '4');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `url` text NOT NULL,
  `parentID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `news`
--

CREATE TABLE `news` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `link` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `dateSet` date NOT NULL,
  `timeSet` time NOT NULL DEFAULT current_timestamp(),
  `totalPrice` int(11) NOT NULL,
  `cusID` int(11) NOT NULL,
  `isCompleted` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `dateSet`, `timeSet`, `totalPrice`, `cusID`, `isCompleted`) VALUES
(1, '2023-12-09', '00:00:00', 100000, 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `brandID` int(11) NOT NULL,
  `kind` varchar(250) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `name`, `brandID`, `kind`, `description`) VALUES
(1, 'may x quang', 1, 'C', 'hien dai hang dau the gioi'),
(2, 'may xet nghiem mau', 2, 'C', 'may xet nghiem mau nhanh nhat the gioi'),
(3, 'khau trang thinh phat', 3, 'A', 'khau trang thinh phat xuat xu Viet Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `units`
--

CREATE TABLE `units` (
  `imei` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `color` varchar(250) DEFAULT NULL,
  `size` varchar(250) DEFAULT NULL,
  `wattage` float DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `yearMade` int(11) DEFAULT NULL,
  `madeIn` varchar(250) DEFAULT NULL,
  `dateImport` date DEFAULT NULL,
  `available` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `units`
--

INSERT INTO `units` (`imei`, `productID`, `color`, `size`, `wattage`, `price`, `amount`, `yearMade`, `madeIn`, `dateImport`, `available`) VALUES
(1, 1, 'trang', 'L', 30, 10000000, 11, 2020, 'Trung Quoc', '2023-12-01', 1),
(2, 1, 'den', 'M', 20, 7000000, 10, 2020, 'Trung Quoc', '2023-12-01', 1),
(3, 2, NULL, 'L', 30, 10000000, 100, 2022, 'My', '2023-12-01', 1),
(4, 3, 'trang', 'M', NULL, 250, 100000, 2023, 'Trung Quoc', '2023-12-01', 1),
(5, 3, 'xanh nuoc', 'M', NULL, 250, 100000000, 2023, 'Trung Quoc', '2023-12-01', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `pwd` text NOT NULL,
  `level` int(11) NOT NULL,
  `phone` varchar(250) NOT NULL,
  `address` text NOT NULL,
  `branchID` int(11) DEFAULT NULL,
  `info` text NOT NULL,
  `dateIn` date NOT NULL DEFAULT current_timestamp(),
  `dateOut` date DEFAULT NULL,
  `available` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `pwd`, `level`, `phone`, `address`, `branchID`, `info`, `dateIn`, `dateOut`, `available`) VALUES
(1, 'To Minh Nhat', '21130463@st.hcmuaf.edu.vn', '1234', 2, '0385135580', 'xuan dinh - xuan loc - dong nai', 1, 'nam - 04/10/2003 - it - truong phong', '2023-12-18', NULL, 1),
(2, 'Nguyen Trong Tin', '18130244@st.hcmuaf.edu.vn', '2345', 2, '0385135589', 'cu chi - ho chi minh', 1, 'nam - 1/1/2000 - it - truong phong B', '2023-12-18', NULL, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `branches`
--
ALTER TABLE `branches`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `de_orders`
--
ALTER TABLE `de_orders`
  ADD PRIMARY KEY (`ordID`,`imei`),
  ADD KEY `imei` (`imei`);

--
-- Chỉ mục cho bảng `home_contents`
--
ALTER TABLE `home_contents`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parentID` (`parentID`);

--
-- Chỉ mục cho bảng `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cusID` (`cusID`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `brandID` (`brandID`);

--
-- Chỉ mục cho bảng `units`
--
ALTER TABLE `units`
  ADD PRIMARY KEY (`imei`),
  ADD KEY `productID` (`productID`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `branchID` (`branchID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `branches`
--
ALTER TABLE `branches`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `news`
--
ALTER TABLE `news`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `units`
--
ALTER TABLE `units`
  MODIFY `imei` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `de_orders`
--
ALTER TABLE `de_orders`
  ADD CONSTRAINT `de_orders_ibfk_1` FOREIGN KEY (`ordID`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `images_ibfk_1` FOREIGN KEY (`parentID`) REFERENCES `home_contents` (`id`),
  ADD CONSTRAINT `images_ibfk_2` FOREIGN KEY (`parentID`) REFERENCES `news` (`id`),
  ADD CONSTRAINT `images_ibfk_3` FOREIGN KEY (`parentID`) REFERENCES `units` (`imei`),
  ADD CONSTRAINT `images_ibfk_4` FOREIGN KEY (`parentID`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cusID`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`brandID`) REFERENCES `brands` (`id`);

--
-- Các ràng buộc cho bảng `units`
--
ALTER TABLE `units`
  ADD CONSTRAINT `units_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`branchID`) REFERENCES `branches` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
