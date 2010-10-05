CREATE TABLE info_dynamic (
    id          INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    info_id     INTEGER NOT NULL,
    info_code   VARCHAR(20) NOT NULL,
    info_value  VARCHAR(20) NOT NULL,
    CONSTRAINT unique_info_dynamic_info_id_info_code_info_value UNIQUE (info_id, info_code, info_value)
)