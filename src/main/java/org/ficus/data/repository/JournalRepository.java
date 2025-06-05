package org.ficus.data.repository;

import org.ficus.data.entity.Journal;
import org.ficus.data.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Long> {

    @Query("SELECT j FROM Journal j JOIN j.schedule s WHERE s.groups.id = :groupId AND j.lessonDate = :lessonDate")
    List<Journal> findByGroupIdAndLessonDate(@Param("groupId") Long groupId, @Param("lessonDate") Date lessonDate);

    @Procedure(procedureName = "update_journal_entries")
    void updateJournalEntry(@Param("p_schedule_id") Long scheduleId,
                            @Param("p_student_id") Long studentId,
                            @Param("p_attendance") Boolean attendance,
                            @Param("p_grade") String score);


}
