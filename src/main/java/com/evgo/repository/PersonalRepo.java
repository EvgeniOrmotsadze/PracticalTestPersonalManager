package com.evgo.repository;

import com.evgo.model.AddTask;
import com.evgo.model.LoginUser;
import com.evgo.model.Task;
import com.evgo.model.User;

import java.util.List;

/**
 * Created by root_pc on 11/13/2016.
 */
public interface PersonalRepo {

    public User getUser(LoginUser user);

    public Boolean addTask(AddTask task,int userId);

    public List<Task> getAllTask(int userId);
}
