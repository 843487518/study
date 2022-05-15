package com.zxk.study.module;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


/**
* Table:sys_user  用户表
* @author zhouxx
* @create	2022-04-26 16:39:26
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDTO implements Serializable {

		 private static final long serialVersionUID = 1L;

		 /**	bigint(11)	PRI*/
		 private 	Long	id;
		 /**用户名	varchar(32)	*/
		 private 	String	userName;
		 /**加盐后用户密码	varchar(32)	*/
		 private 	String	userPwd;
		 /**手机号	varchar(20)	*/
		 private 	String	userPhone;
		 /**邮箱地址	varchar(100)	*/
		 private 	String	userEmail;
		 /**性别，0-男；1-女	tinyint(4)	*/
		 private 	Integer	userSex;
		 /**创建时间	timestamp	*/
		 private 	Date 	createTime;
		 /**最后修改时间	timestamp	*/
		 private 	Date	updateTime;
		 /**是否删除，0-未删除；1-已删除	tinyint(4)	*/
		 private 	Integer	deleteFlag;

}
