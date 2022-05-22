package	com.example.module.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
* Table:sys_user_role  用户与角色对应关系
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleBO implements Serializable {

		 private static final long serialVersionUID = 1L;

		 /**	bigint(20)	PRI*/
		 private 	Long	id;
		 /**用户ID	bigint(20)	*/
		 private 	Long	userId;
		 /**角色ID	bigint(20)	*/
		 private 	Long	roleId;
		 /**创建者ID	bigint(20)	*/
		 private 	Long	createUserId;
		 /**创建时间	timestamp	*/
		 private 	Date	createTime;
		 /**最后修改时间	timestamp	*/
		 private 	Date	updateTime;
		 /**是否删除，0-未删除；1-已删除	tinyint(4) unsigned zerofill	*/
		 private 	Integer	deleteFlag;

}
