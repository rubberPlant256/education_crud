package org.ficus.data.repository;

import org.ficus.data.entity.Course;
import org.ficus.data.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
