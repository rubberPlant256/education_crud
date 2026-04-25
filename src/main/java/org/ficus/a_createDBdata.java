//package org.ficus;
//
//
//import lombok.RequiredArgsConstructor;
//import org.ficus.auth.config.BCrypt.BCryptPasswordEncoder;
//import org.ficus.data.entity.*;
//import org.ficus.data.repository.*;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class a_createDBdata {
//
//    private final UserRepository userRepository;
//    private final TeacherRepository teacherRepository;
//    private final BCryptPasswordEncoder encoder;
//    private final CourseRepository courseRepository;
//    private final LessonRepository lessonRepository;
//    private final GroupsRepository groupsRepository;
//    private final ScheduleRepository scheduleRepository;
//
//    /**
//     * при запуске приложения заполняет БД тестовыми данными
//     */
//    @EventListener(ApplicationReadyEvent.class)
//    public void fillRoleEntities() {
//
//        Users user = new Users();
//        user.setRole(Role.TEACHER);
//        user.setLogin("111");
//        user.setPassword(encoder.encode("111"));
//     //   user = userRepository.save(user);
//
//        Course course = new Course();
//        course.setName("ggggggg");
//        course.setDuration(BigDecimal.valueOf(1.5));
//        course.setMin_students(3);
//        course.setMax_students(15);
//        course.setLessons_count(10);
//        course.setPrice(BigDecimal.valueOf(10000));
//        courseRepository.save(course);
//
//        List<Course> courseList = new ArrayList<>();
//        courseList.add(course);
//
//        Lesson lesson = new Lesson();
//        lesson.setDay(Day.FRIDAY);
//        lesson.setTime(Time.EIGHTEEN);
//        lessonRepository.save(lesson);
//
//        Groups groups = new Groups();
//        groups.setCourse(course);
//        groupsRepository.save(groups);
//
//        List<Schedule> scheduleList = new ArrayList<>();
//
//        Teacher teacher = new Teacher();
//        teacher.setLastName("Ухов");
//        teacher.setFirstName("Владимир");
//        teacher.setMiddleName("Менеджерович");
//        teacher.setPhone("89005353535");
//        teacher.setCourses(courseList);
//    //    teacher.setSchedules(scheduleList);
//        teacher.setUser(user);
//        teacherRepository.save(teacher);
//
//        Schedule schedule = new Schedule();
//        schedule.setLesson(lesson);
//        schedule.setGroups(groups);
//        schedule.setTeacher(teacher);
//        scheduleRepository.save(schedule);
//
////        Users user1 = new Users();
////        user1.setRole(Role.TEACHER);
////        user1.setLogin("33");
////        user1.setPassword(encoder.encode("33"));
////        user1 = userRepository.save(user1);
////
////        Teacher teacher1 = new Teacher();
////        teacher1.setLastName("Филатов");
////        teacher1.setFirstName("Пуп");
////        teacher1.setMiddleName("Пупович");
////        teacher1.setPhone("89005353535");
////        teacher1.setUser(user1);
////        managerRepository.save(teacher1);
////
////        Users userAdmin = new Users();
////        userAdmin.setRole(Role.ADMINISTRATOR);
////        userAdmin.setLogin("admin");
////        userAdmin.setPassword(encoder.encode("admin"));
////        userRepository.save(userAdmin);
//    }
//}
