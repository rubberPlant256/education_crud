 CREATE OR REPLACE FUNCTION update_journal_entries(
                  p_schedule_id BIGINT,
                  p_student_id BIGINT,
                  p_attendance BOOLEAN,
                  p_score VARCHAR
                ) RETURNS VOID AS $$
                  BEGIN
                    -- Проверяем существование записи в журнале
                    IF EXISTS (
                      SELECT 1 FROM journal
                      WHERE schedule_id = p_schedule_id
                      AND student_id = p_student_id
                    ) THEN
                      -- Обновляем существующую запись
                      UPDATE journal
                      SET
                        attendance = p_attendance,
                        score = p_score
                      WHERE
                        schedule_id = p_schedule_id
                        AND student_id = p_student_id;
                    END IF;
                  END;
                  $$ LANGUAGE plpgsql;