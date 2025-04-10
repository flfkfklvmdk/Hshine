package org.example.tails.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tails.pojo.emp.Emp;
import org.example.tails.pojo.stu.StuQueryParam;
import org.example.tails.pojo.stu.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {
    /**
     * 分页查询
     */
    List<Student> list(StuQueryParam stuQueryParam);

    /**
     * 批量删除
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 添加员工
     */
    @Options
    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time)" +
            "values(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void save(Student student);

    /**
     * 查询回显
     */
    @Select("select * from student where id = #{id}")
    Student getInfo(Integer id);

    /**
     * 更新员工基本信息
     */
    void update(Student student);

    //统计学院学历数据
    @MapKey("degree")
    List<Map> countStudentDegreeData();

    //统计班级人数
    List<Map> countClassData();
}
