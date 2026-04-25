package org.ficus.service.converter;

import org.ficus.data.entity.Parent;
import org.ficus.data.entity.Teacher;
import org.ficus.dto.ParentDTO;
import org.ficus.dto.TeacherDTO;

public class ParentToParentDTO {

    public static ParentDTO convertParentToParentDTO(Parent parent){
       ParentDTO parentDTO = new ParentDTO();
       parentDTO.setId(parent.getId());
       parentDTO.setLastName(parent.getLastName());
       parentDTO.setFirstName(parent.getFirstName());
       parentDTO.setMiddleName(parent.getMiddleName());
       parentDTO.setPhone(parent.getPhone());

        return parentDTO;
    }
}
