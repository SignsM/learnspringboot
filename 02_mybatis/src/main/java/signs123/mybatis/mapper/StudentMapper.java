package signs123.mybatis.mapper;



import org.apache.ibatis.annotations.Mapper;
import signs123.mybatis.bean.Student;

import java.util.List;


@Mapper
public interface StudentMapper {
    List<Student> selectAllStudent();
}
