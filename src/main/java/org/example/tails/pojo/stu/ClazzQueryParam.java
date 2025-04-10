package org.example.tails.pojo.stu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzQueryParam {
    private String name;//班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Local beginDate;//开课时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Local endDate;//结课时间
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
}
