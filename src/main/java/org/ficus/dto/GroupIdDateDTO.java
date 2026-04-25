package org.ficus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupIdDateDTO {

    private Long GroupId;

    private Date lessonDate;
}
