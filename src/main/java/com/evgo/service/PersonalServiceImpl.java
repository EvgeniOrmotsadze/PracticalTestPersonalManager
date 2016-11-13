package com.evgo.service;

import com.evgo.model.AddTask;
import com.evgo.model.LoginUser;
import com.evgo.model.Task;
import com.evgo.model.User;
import com.evgo.repository.PersonalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by root_pc on 11/13/2016.
 */

@Service
@Transactional
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    PersonalRepo userRepo;

    public User getUser(LoginUser users) {
        return userRepo.getUser(users);
    }

    public Boolean addTask(AddTask task,int userId) {
        return userRepo.addTask(task,userId);
    }

    public List<Task> getAllTask(int userId) {
        return userRepo.getAllTask(userId);
    }
}
