package com.zxk.study.module.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


/**
* Table:sys_role  角色
* @author zhouxx
* @create	2022-05-17 20:35:39
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRoleDTO implements Serializable {

		 private static final long serialVersionUID = 1L;

		 /**	bigint(20)	PRI*/
		 private 	Long	role_id;
		 /**角色名称	varchar(100)	*/
		 private 	String	roleName;
		 /**备注	varchar(100)	*/
		 private 	String	remark;
		 /**创建者ID	bigint(20)	*/
		 private 	Long	createUserId;
		 /**创建时间	timestamp	*/
		 private Date createTime;
		 /**最后修改时间	timestamp	*/
		 private 	Date	updateTime;
		 /**是否删除，0-未删除；1-已删除	tinyint(4)	*/
		 private 	Integer	deleteFlag;

}
