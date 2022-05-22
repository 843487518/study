package	com.example.controller;


import com.example.module.vo.input.MenuInput;
import com.example.service.MenuService;
import com.example.module.dto.JmMenuDTO;
import com.zxx.study.web.util.PassToken;
import com.zxx.study.web.module.ApiResult;
import com.zxx.study.web.util.DateUtils;
import com.zxx.study.web.common.commenum.BaseResultError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
* Table:jm_menu  菜单管理
* @author zhouxx
* @create	2022-05-22 20:20:49
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/menu")
public class MenuController {

		 @Autowired
		 MenuService menuService;

		 @GetMapping("/detail/{id}")
		 public ApiResult getDetail(@PathVariable("id") long id){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        jmMenuDTO.setId(id); 
		        return ApiResult.success(menuService.query(jmMenuDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody MenuInput menuInput){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        BeanUtils.copyProperties(menuInput, jmMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(menuService.queryList(jmMenuDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody MenuInput menuInput){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        BeanUtils.copyProperties(menuInput, jmMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //jmMenuDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= menuService.add(jmMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody MenuInput menuInput){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        BeanUtils.copyProperties(menuInput, jmMenuDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        jmMenuDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= menuService.modify(jmMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        JmMenuDTO jmMenuDTO=new JmMenuDTO();
		        jmMenuDTO.setId(id); 
		        int cnt= menuService.delete(jmMenuDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}
