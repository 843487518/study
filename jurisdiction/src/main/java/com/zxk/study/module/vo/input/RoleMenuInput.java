package com.zxk.study.module.vo.input;

import java.io.Serializable;
import java.util.Date;

public class RoleMenuInput implements Serializable {

    private static final long serialVersionUID = 1L;

    /**	bigint(20)	PRI*/
    private 	Long	id;
    /**角色ID	bigint(20)	*/
    private 	Long	roleId;
    /**菜单ID	bigint(20)	*/
    private 	Long	menuId;
    /**查询权限	tinyint(4)	*/
    private 	Integer	queryAuthority;
    /**创建权限	tinyint(4)	*/
    private 	Integer	createAuthority;
    /**修改权限	tinyint(4)	*/
    private 	Integer	updateAuthority;
    /**删除权限	tinyint(4)	*/
    private 	Integer	deleteAuthority;
    /**创建者ID	bigint(20)	*/
    private 	Long	createUserId;
    /**创建时间	timestamp	*/
    private Date createTime;
    /**最后修改时间	timestamp	*/
    private 	Date	updateTime;
    /**是否删除，0-未删除；1-已删除	tinyint(4)	*/
    private 	Integer	deleteFlag;

}