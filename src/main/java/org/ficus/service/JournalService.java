package org.ficus.service;

import lombok.RequiredArgsConstructor;
import org.ficus.data.entity.Journal;
import org.ficus.data.entity.Parent;
import org.ficus.data.entity.Score;
import org.ficus.data.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;

    public List<Journal> findJournalByGroupIdAndLessonDate(Long groupId, Date lessonDate){
        return journalRepository.findByGroupIdAndLessonDate(groupId, lessonDate);
    }

    public void updateJournalEntry(Long scheduleId, Long studentId,
                                   Boolean attendance, Score score) {
        // Вызываем хранимую процедуру через репозиторий
        journalRepository.updateJournalEntry(
                scheduleId,
                studentId,
                attendance,
                score != null ? score.name() : null
               // score.name()  // преобразуем enum в String
        );
    }
}
