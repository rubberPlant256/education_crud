package org.ficus.service;

import lombok.RequiredArgsConstructor;
import org.ficus.data.entity.Parent;
import org.ficus.data.entity.Score;
import org.ficus.data.entity.Student;
import org.ficus.data.entity.Users;
import org.ficus.data.repository.ParentRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    public Parent save(Parent parent) {
        return parentRepository.save(parent);
    }

    public Parent findParentByUserId(Long id) {
        return parentRepository.findParentByUser_Id(id);
    }

    public Parent createEmptyParent(Users user){
        Parent parent = new Parent();
        parent.setLastName(null);
        parent.setFirstName(null);
        parent.setMiddleName(null);
        parent.setPhone(null);
        parent.setUser(user);

        return save(parent);
    }

    public void updateParent(Long parentId, String lastName,
                             String firstName, String middleName, String phone) {
       parentRepository.updateParent(
               parentId,
               lastName,
               firstName,
               middleName,
               phone
       );
    }

}
