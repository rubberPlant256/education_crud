package org.ficus.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "course")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Integer min_students;

    private Integer max_students;

    private BigDecimal duration;

    private Integer lessons_count;

    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Groups> groups;

    @ManyToMany(mappedBy = "courses")
    private List<Teacher> teachers;
}
