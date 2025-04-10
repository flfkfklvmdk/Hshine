package org.example.tails.pojo.emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpLog {
    private Integer id;//ID
    private LocalDateTime operationTime;//操作时间
    private String operation;//详细信息
}
