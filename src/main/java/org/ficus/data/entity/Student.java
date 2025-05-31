package org.ficus.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private Date birthDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToMany(mappedBy = "students")
    private List<Groups> groups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Journal> journals;
}
