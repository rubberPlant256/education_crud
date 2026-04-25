DROP FUNCTION update_parent(bigint,character varying,character varying,character varying,character varying);

CREATE OR REPLACE FUNCTION update_parent(
  p_id BIGINT,  -- Изменили имя параметра с p_parent_id на p_id
  p_last_name VARCHAR,
  p_first_name VARCHAR,
  p_middle_name VARCHAR,
  p_phone VARCHAR
) RETURNS VOID AS $$
BEGIN
  -- Проверяем существование записи в таблице parent
  IF EXISTS (
    SELECT 1 FROM parent
    WHERE id = p_id  -- Используем правильное имя столбца id
  ) THEN
    -- Обновляем существующую запись
    UPDATE parent
    SET
      last_name = p_last_name,
      first_name = p_first_name,
      middle_name = p_middle_name,
      phone = p_phone
    WHERE
      id = p_id;  -- Используем правильное имя столбца id
  END IF;
END;
$$ LANGUAGE plpgsql;