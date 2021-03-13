CREATE TABLE IF NOT EXISTS searchresult (
    id SERIAL NOT NULL PRIMARY KEY,
    search_date DATE NOT NULL,
    condition TEXT NOT NULL,
    search_result JSON NOT NULL,
    page_num SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS foodresult (
    id SERIAL NOT NULL PRIMARY KEY,
    create_date DATE NOT NULL,
    condition TEXT NOT NULL,
    result JSON NOT NULL
);