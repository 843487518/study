package com.zxk.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/26  17:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String memberName;

}
