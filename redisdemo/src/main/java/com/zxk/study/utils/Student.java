package com.zxk.study.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/7  13:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    Long id;
    String name;
    Integer age;
}
