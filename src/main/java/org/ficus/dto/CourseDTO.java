package org.ficus.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;

    private String name;

    private Integer min_students;

    private Integer max_students;

    private BigDecimal duration;

    private Integer lessons_count;

    private BigDecimal price;
}
