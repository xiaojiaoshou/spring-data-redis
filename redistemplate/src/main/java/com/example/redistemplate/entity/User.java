package com.example.redistemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @类描述: 用户信息
 * @author guohuixiang
 * @date 2019/8/5
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    private String userName;
    private String sex;
    private Integer age;
}
