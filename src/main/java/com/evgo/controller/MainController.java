package com.evgo.controller;

import com.evgo.model.AddTask;
import com.evgo.model.LoginUser;
import com.evgo.model.Task;
import com.evgo.model.User;
import com.evgo.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by root_pc on 11/13/2016.
 */


@Controller
@RequestMapping(value = "/")
public class MainController {


    @Autowired
    PersonalService personalService;

    @RequestMapping(value = {"/", "/home"})
    public String render() {
        return "static/home-page.html";
    }


    @Autowired
    HttpSession session;

    @RequestMapping(value = "/login",  method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User login(@RequestBody LoginUser user){
        User findUser =  personalService.getUser(user);
        if(findUser != null){
            session.setAttribute("user",findUser);
        }
        return findUser;
    }


    @RequestMapping(value = "/addTask",  method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Boolean addTask(@RequestBody AddTask task){
        System.out.println(((User)session.getAttribute("user")).getId());
        return personalService.addTask(task,((User)session.getAttribute("user")).getId());
    }

    @RequestMapping(value = "/showAllTask",  method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<Task> showTask(){
        System.out.println(((User)session.getAttribute("user")).getId());
        return personalService.getAllTask(((User)session.getAttribute("user")).getId());
    }









}
