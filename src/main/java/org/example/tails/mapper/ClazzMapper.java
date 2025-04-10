package org.example.tails.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tails.pojo.stu.Clazz;
import org.example.tails.pojo.stu.ClazzQueryParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ClazzMapper {
    // 查询班级信息
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    //删除班级信息
    @Delete("DELETE FROM clazz WHERE id = #{id}")
    void deleteById(Integer id);

    //添加班级
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO clazz (name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
            "VALUES (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    //根据id查询班级信息
    @Select("SELECT * FROM clazz WHERE id = #{id}")
    Clazz getById(Integer id);

    //修改班级信息
    void updateById(Clazz clazz);

    //岔村所有班级信息
    @Select("SELECT * FROM clazz")
    List<Clazz> getAllClazzs();
}
