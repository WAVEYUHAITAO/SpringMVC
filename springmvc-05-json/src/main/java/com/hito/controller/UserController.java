package com.hito.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hito.pojo.User;
import com.hito.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
@RestController//restcontroller代表这个类下的所有方法都不走视图解析，而是返回字符串
public class UserController {
    //原生解决json乱码
    //@RequestMapping(value = "/j1", produces = "application/json;charset=utf-8")
    @RequestMapping("/j1")
    //@ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json1() throws JsonProcessingException {

        //jackson, ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user = new User("海涛1号",3,"男");
        String str = mapper.writeValueAsString(user);
        return str;
    }

    @RequestMapping("/j2")
    public String json2() throws JsonProcessingException {
        //ObjectMapper mapper = new ObjectMapper();

        List<User> userList = new ArrayList<>();

        User user1 = new User("海涛1号",3,"男");
        User user2 = new User("海涛1号",3,"男");
        User user3= new User("海涛1号",3,"男");
        User user4 = new User("海涛1号",3,"男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        //String str = mapper.writeValueAsString(userList);
        //return str;
        return JsonUtils.getJson(userList);
    }

    @RequestMapping("/j3")
    public String json3() throws JsonProcessingException {
        //ObjectMapper mapper = new ObjectMapper();

        //使用ObjectMapper来格式化输出
        //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        //自定义日期格式
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        //Date date = new Date();
        //mapper.setDateFormat(sdf);
        //ObjectMapper, 时间解析后的默认格式为：Timestamp,时间戳
        //return mapper.writeValueAsString(sdf.format(date));
        //return mapper.writeValueAsString(date);

        //利用Json工具类实现日期的自定义格式输出
        return JsonUtils.getJson(new Date());
    }

    @RequestMapping("/j4")
    public String json4() throws JsonProcessingException {

        List<User> userList = new ArrayList<>();

        User user1 = new User("海涛1号",3,"男");
        User user2 = new User("海涛2号",3,"男");
        User user3= new User("海涛3号",3,"男");
        User user4 = new User("海涛4号",3,"男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        System.out.println("*****Java对象转JSON字符串*****");
        String str1 = JSON.toJSONString(userList);
        System.out.println("JSON.toJSONString(userList)==>"+str1);
        String str2 = JSON.toJSONString(user1);
        System.out.println("JSON.toJSONString(user1)==>"+str2);

        System.out.println("*****JSON字符串转Java对象*****");
        User jp_user1 = JSON.parseObject(str2, User.class);
        System.out.println("JSON.parseObject(str2, User.class)==>"+jp_user1);

        System.out.println("*****Java对象转JSON对象*****");
        JSONObject jsonObject1 = (JSONObject)JSON.toJSON(user2);
        System.out.println("JAVA对象user2==>"+user2);
        System.out.println("(JSONObject)JSON.toJSON(user2)==>"+jsonObject1);

        return "FastJSON";
    }
}
