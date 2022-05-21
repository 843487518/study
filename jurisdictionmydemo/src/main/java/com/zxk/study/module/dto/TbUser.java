package com.zxk.study.module.dto;


//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class TbUser implements Serializable {

		 private static final long serialVersionUID = 1L;

//		 @TableId(value = "user_id")
		 /**	bigint(20)	PRI*/
		 private 	Long	user_id;
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
