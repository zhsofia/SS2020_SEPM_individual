CREATE TYPE IF NOT EXISTS race AS ENUM ('ARABIAN','MORGAN','PAINT','APPALOOSA');

CREATE TABLE IF NOT EXISTS owner
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    updated_at DATETIME     NOT NULL
);

CREATE TABLE IF NOT EXISTS horse
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    race        race         NOT NULL,
    rating      INT          NOT NULL,
    birth_date  DATE         NOT NULL,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL,
    id_of_owner BIGINT       NOT NULL,
    image BLOB,
    CHECK (rating > 0 AND rating < 6),
    FOREIGN KEY (id_of_owner) REFERENCES owner (id)
);

