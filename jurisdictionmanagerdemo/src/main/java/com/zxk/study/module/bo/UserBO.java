package com.zxk.study.module.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


/**
* Table:tb_user  用户
* @author zhouxx
* @create	2022-05-17 20:35:40
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO implements Serializable {

		 private static final long serialVersionUID = 1L;

		 /**	bigint(20)	PRI*/
		 private 	Long	userId;
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
