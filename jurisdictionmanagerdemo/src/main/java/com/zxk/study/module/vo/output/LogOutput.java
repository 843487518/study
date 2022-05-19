package com.zxk.study.module.vo.output;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


/**
* Table:sys_log  系统日志
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogOutput implements Serializable {

		 private static final long serialVersionUID = 1L;

		 /**	bigint(20)	PRI*/
		 private 	Long	id;
		 /**用户名	varchar(50)	*/
		 private 	String	username;
		 /**用户操作	varchar(50)	*/
		 private 	String	operation;
		 /**请求方法	varchar(200)	*/
		 private 	String	method;
		 /**请求参数	varchar(5000)	*/
		 private 	String	params;
		 /**执行时长(毫秒)	bigint(20)	*/
		 private 	Long	time;
		 /**IP地址	varchar(64)	*/
		 private 	String	ip;
		 /**创建时间	datetime	*/
		 private Date createDate;

}
