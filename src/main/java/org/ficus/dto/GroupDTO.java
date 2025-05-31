package org.ficus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ficus.data.entity.Course;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

    private Long id;

    private Course course;


}
