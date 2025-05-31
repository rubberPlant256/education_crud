package org.ficus.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "lesson")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Time time;

    @Enumerated(value = EnumType.STRING)
    private Day day;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<Schedule> schedules;
}
