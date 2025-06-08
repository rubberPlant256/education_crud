package org.ficus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String phone;

}
