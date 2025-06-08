 CREATE OR REPLACE FUNCTION update_parent(
                  p_parent_id BIGINT,
                  p_last_name VARCHAR,
                  p_first_name VARCHAR,
                  p_middle_name VARCHAR,
                  p_phone VARCHAR
                ) RETURNS VOID AS $$
                  BEGIN
                    -- Проверяем существование записи в журнале
                    IF EXISTS (
                      SELECT 1 FROM parent
                      WHERE parent_id = p_parent_id
                    ) THEN
                      -- Обновляем существующую запись
                      UPDATE parent
                      SET
                        last_name = p_last_name,
                        first_name = p_first_name,
                        middle_name = p_middle_name,
                        phone = p_phone
                      WHERE
                        parent_id = p_parent_id;
                    END IF;
                  END;
                  $$ LANGUAGE plpgsql;