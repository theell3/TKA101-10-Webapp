CREATE DATABASE IF NOT EXISTS taipeigo;

USE taipeigo;

DROP TABLE IF EXISTS ticketCategory;


-- =======================================================
-- 1. 建立門票分類表 (ticketCategory)
-- =======================================================

CREATE TABLE ticketCategory (
    ticketCategoryId      INT AUTO_INCREMENT NOT NULL COMMENT '門票分類編號',
    ticketCategoryName    VARCHAR(50) NOT NULL COMMENT '門票分類名稱',
    ticketCategoryStatus  TINYINT NOT NULL COMMENT '門票分類啟用狀態 0=未啟用 1=啟用',
    CONSTRAINT ticketCategoryPK PRIMARY KEY (ticketCategoryId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='門票分類';

-- 插入門票分類測試資料 (ID 會預設從 1 開始，每次+1 遞增)
INSERT INTO ticketCategory (ticketCategoryName, ticketCategoryStatus) VALUES ('博物館', 1);
INSERT INTO ticketCategory (ticketCategoryName, ticketCategoryStatus) VALUES ('公園', 1);
INSERT INTO ticketCategory (ticketCategoryName, ticketCategoryStatus) VALUES ('歷史景點', 1);
INSERT INTO ticketCategory (ticketCategoryName, ticketCategoryStatus) VALUES ('美術館', 1);
INSERT INTO ticketCategory (ticketCategoryName, ticketCategoryStatus) VALUES ('主題樂園', 1);