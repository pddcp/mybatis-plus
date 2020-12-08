package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @TableId(type = IdType.INPUT)
    private long id;
    private String name;
    private Integer age;
    private String email;
    @Version//增加version
    private Integer version;
//    逻辑删除
//    @TableLogic
//    private Integer deleted;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}
