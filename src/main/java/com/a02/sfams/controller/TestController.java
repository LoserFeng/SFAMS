package com.a02.sfams.controller;

import com.a02.sfams.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController

public class TestController {


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){

        return "hello world";
    }



    @RequestMapping(value="/postTest1",method = RequestMethod.POST)
    public String postTest1(@RequestParam(value = "name",required = true) String  name, @RequestParam(value = "pwd",required = true)String password){


        String res="";
        System.out.println(name+" "+password);

        if( name.equals("loserfeng")&&password.equals("110900fyf")){
            res="Success!";

        }else{
            res="false";

        }
        res=res+"\n"+name+password;
        return res;
    }

    @RequestMapping(value="/postTest2",method = RequestMethod.POST)
    public String postTest2(User user){


        System.out.println(user);



        return "POSTTest2";
    }


    // json类型传输

    @RequestMapping(value="/postTest3",method = RequestMethod.POST)
    public String postTest3(@RequestBody User user){


        System.out.println(user);

        return "POSTTest3";
    }


}
