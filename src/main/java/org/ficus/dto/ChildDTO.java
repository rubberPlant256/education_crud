package org.ficus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private Date birthDate;

    private List<String> childGroups;
}
