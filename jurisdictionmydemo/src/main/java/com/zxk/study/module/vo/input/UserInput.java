package com.zxk.study.module.vo.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInput implements Serializable {

    private static final long serialVersionUID = 1L;

    /**用户名	varchar(50)	UNI*/
    private 	String	username;
    /**手机号	varchar(20)	*/
    private 	String	mobile;
    /**密码	varchar(64)	*/
    private 	String	password;
    /**创建时间	timestamp	*/
    private Date createTime;
    /**最后修改时间	timestamp	*/
    private 	Date	updateTime;
    /**是否删除，0-未删除；1-已删除	tinyint(4)	*/
    private 	Integer	deleteFlag;

}