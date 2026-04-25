package org.ficus.service.converter;

import org.ficus.data.entity.Journal;
import org.ficus.data.entity.Schedule;
import org.ficus.data.entity.Score;
import org.ficus.data.entity.Student;
import org.ficus.dto.JournalDTO;
import org.ficus.dto.ScheduleDTO;

import java.util.Date;

public class JournalToJournalDTO {

    public static JournalDTO convertJournalToJournalDTO(Journal journal){

        JournalDTO journalDTO = new JournalDTO();
        journalDTO.setId(journal.getId());
        journalDTO.setSchedule(journal.getSchedule());
        journalDTO.setStudent(journal.getStudent());
        journalDTO.setScore(journal.getScore());
        journalDTO.setAttendance(journal.isAttendance());
        journalDTO.setLessonDate(journal.getLessonDate());

        return journalDTO;
    }


}
