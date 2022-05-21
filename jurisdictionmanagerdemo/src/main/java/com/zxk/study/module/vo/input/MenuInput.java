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
public class MenuInput implements Serializable {
    private static final long serialVersionUID = 1L;

    /**父菜单ID，一级菜单为0	bigint(20)	*/
    private 	Long	parentId;
    /**菜单名称	varchar(50)	*/
    private 	String	icon;
    /**创建者ID	bigint(20)	*/
    private 	Long	createUserId;
    /**创建时间	timestamp	*/
    private Date createTime;
    /**最后修改时间	timestamp	*/
    private 	Date	updateTime;
    /**是否删除，0-未删除；1-已删除	tinyint(4)	*/
    private 	Integer	deleteFlag;

}