package com.lypowernode.controller;

import com.lypowernode.domain.Student;
import com.lypowernode.service.StudentService;
import com.lypowernode.service.impl.StudentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void daPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        String strName = request.getParameter("name");
        String strEmail = request.getParameter("email");
        String strAge = request.getParameter("age");


        //

        // 创建spring的容器对象方法一 :
        // String config = "applicationContext.xml";
        // ApplicationContext ac = new ClassPathXmlApplicationContext(config);
       /*
       * WebApplicationContext继承ApplicationContext
       * ApplicationContext 是 : javase项目中的容器对象
       * WebApplicationContext 是 : web项目中使用的容器对象
        * */
        WebApplicationContext ac = null;

        // 获取servletContext中的容器对象 , 创建好的容器对象 , 拿来就用
       /*
       String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
        Object attr = getServletContext().getAttribute(key);
        if (attr !=null){
             ac = (WebApplicationContext) attr;
        }
        */


        // 使用框架中的方法 , 获取容器对象
        ServletContext sc = getServletContext();
        // 参数ServletContext对象
         ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        System.out.println(" 容器对象的信息 ======"+ ac);
        // 获取service
        StudentService service = (StudentService) ac.getBean("studentService");
        Student student = new Student();
        student.setId(Integer.parseInt(strId));
        student.setName(strName);
        student.setEmail(strEmail);
        student.setAge(Integer.valueOf(strAge));
        service.addStudent(student);

        // 给一个页面
        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }


}
