 CREATE OR REPLACE PROCEDURE update_journal_entries(
                  p_schedule_id BIGINT,
                  p_student_id BIGINT,
                  p_attendance BOOLEAN,
                  p_grade VARCHAR
                ) AS $$
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
                        grade = p_grade,
                        updated_at = NOW()
                      WHERE
                        schedule_id = p_schedule_id
                        AND student_id = p_student_id;
                    END IF;
                  END;
                  $$ LANGUAGE plpgsql;