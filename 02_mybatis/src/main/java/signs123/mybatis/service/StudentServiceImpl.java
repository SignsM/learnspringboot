package signs123.mybatis.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import signs123.mybatis.bean.Student;
import signs123.mybatis.mapper.StudentMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
  private StudentMapper studentMapper;
    @Override
    public List<Student> selectAllStudent() {
        return studentMapper.selectAllStudent();
    }
}
