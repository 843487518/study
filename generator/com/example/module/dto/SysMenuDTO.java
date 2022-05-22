package	com.example.module.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
* Table:sys_menu  菜单管理
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuDTO implements Serializable {

		 private static final long serialVersionUID = 1L;

		 /**	bigint(20)	PRI*/
		 private 	Long	menuId;
		 /**父菜单ID，一级菜单为0	bigint(20)	*/
		 private 	Long	parentId;
		 /**菜单名称	varchar(50)	*/
		 private 	String	name;
		 /**菜单URL	varchar(200)	*/
		 private 	String	url;
		 /**菜单图标	varchar(50)	*/
		 private 	String	icon;
		 /**创建者ID	bigint(20)	*/
		 private 	Long	createUserId;
		 /**创建时间	timestamp	*/
		 private 	Date	createTime;
		 /**最后修改时间	timestamp	*/
		 private 	Date	updateTime;
		 /**是否删除，0-未删除；1-已删除	tinyint(4) unsigned zerofill	*/
		 private 	Integer	deleteFlag;

}
