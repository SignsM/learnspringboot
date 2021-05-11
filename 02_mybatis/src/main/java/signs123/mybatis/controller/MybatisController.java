package signs123.mybatis.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import signs123.mybatis.service.StudentService;

@RestController
public class MybatisController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public Object findAllStu(){
        return studentService.selectAllStudent();
    }
}
