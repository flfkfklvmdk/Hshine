package org.example.tails.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tails.pojo.emp.Emp;
import org.example.tails.pojo.emp.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    // ---------------------------------- 原始分页查询实现 -------------------
    /**
     * 查询总记录数
     */
    //@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    //public Long count();

    /**
     * 分页查询
     */
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
    //        "order by e.update_time desc limit #{start},#{pageSize}")
    //public List<Emp> list(Integer start, Integer pageSize);

//
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件查询员工信息
     */
    public List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * 添加员工
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    //批量删除员工基本信息
    void deleteByIds(List<Integer> ids);

    //查询回显
    Emp getInfo(Integer id);

    //更新员工基本信息
    void updateById(Emp emp);

    //统计员工职位基本信息
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    //统计员工性别数据
    @MapKey("name")
    List<Map> countEmpGenderData();

    //查询员工数量
    @Select("select count(*) from emp")
    int countByDeptId(Integer id);

    //根据用户名和密码查询员工信息
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
