CREATE OR REPLACE PROCEDURE add_student(
    p_last_name VARCHAR,
    p_first_name VARCHAR,
    p_middle_name VARCHAR,
    p_birth_date DATE,
    p_parent_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO student (
        last_name,
        first_name,
        middle_name,
        birth_date,
        parent_id
    )
    VALUES (
               p_last_name,
               p_first_name,
               p_middle_name,
               p_birth_date,
               p_parent_id
           );

    COMMIT;
END;
$$;