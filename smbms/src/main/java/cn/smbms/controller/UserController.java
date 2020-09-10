package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import cn.smbms.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

   @Resource
    private UserService userService;

    @RequestMapping("/login.html")
    public String Login(){
        return "login";
    }

    @RequestMapping("/toLogin.html")
    public String toLogin(@RequestParam("userCode") String userCode,
                          @RequestParam("userPassword") String userPassword,
                          HttpSession session,
                          Model model){

        User user = userService.login(userCode,userPassword);
        if (user==null){
            throw new RuntimeException("用户名不存在");
        }else {
            if(!user.getUserPassword().equals(userPassword)){
                throw new RuntimeException("密码不正确");
            }else {
                session.setAttribute(Constants.USER_SESSION, user);
                return "redirect:/user/main.html";
            }
        }

       /* if(null != user){//登录成功
            //放入session
            session.setAttribute(Constants.USER_SESSION, user);
            //页面跳转（frame.jsp）
           return "redirect:/user/main.html";
        }else{
            //页面跳转（login.jsp）带出提示信息--转发
            *//*model.addAttribute("error","用户名或密码错误");
            return "login";*//*
            throw new RuntimeException("用户名或密码不正确");
        }*/
    }

    @RequestMapping("/main.html")
    public String main(HttpSession session){
        if (session.getAttribute(Constants.USER_SESSION)==null){
            return "redirect:/user//login.html";
        }
        return "frame";
    }

    @RequestMapping("/loginout.html")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "login";

    }

   /* 异常处理器*/
   /* @ExceptionHandler(value = {RuntimeException.class})
    public String handleException(RuntimeException e, HttpServletRequest request){
        request.setAttribute("e",e);
        return "login";
    }*/







}
