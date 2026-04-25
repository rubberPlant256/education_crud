package org.ficus.service.converter;

import org.ficus.data.entity.Course;
import org.ficus.data.entity.Groups;
import org.ficus.data.entity.Journal;
import org.ficus.dto.GroupDTO;
import org.ficus.dto.JournalDTO;

import java.util.ArrayList;
import java.util.List;

public class GroupsToGroupsDTO {

    public static List<GroupDTO> convertGroupListToGroupDTOList(List<Groups> groups){

        List<GroupDTO> groupDTOList = new ArrayList<>();

        for (Groups group : groups){
            GroupDTO groupDTO =new GroupDTO();
            groupDTO.setId(group.getId());
            groupDTO.setCourse(group.getCourse().getName());
            groupDTOList.add(groupDTO);
        }

        return groupDTOList;
    }

}
