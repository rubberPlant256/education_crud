package org.ficus.service;

import lombok.RequiredArgsConstructor;
import org.ficus.data.entity.Groups;
import org.ficus.data.entity.Journal;
import org.ficus.data.repository.GroupsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupsService {

    private final GroupsRepository groupsRepository;

    public List<Groups> findGroupsByTeacherId(Long teacherId){
        return groupsRepository.findGroupsByTeacherId(teacherId);
    }


}
