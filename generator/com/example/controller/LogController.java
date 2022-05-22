package	com.example.controller;


import com.example.module.vo.input.LogInput;
import com.example.service.LogService;
import com.example.module.dto.SysLogDTO;
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
* Table:sys_log  系统日志
* @author zhouxx
* @create	2022-05-21 20:16:19
*/
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/log")
public class LogController {

		 @Autowired
		 LogService logService;

		 @GetMapping("/detail/{id}")
		 public ApiResult getDetail(@PathVariable("id") long id){
		        SysLogDTO sysLogDTO=new SysLogDTO();
		        sysLogDTO.setId(id); 
		        return ApiResult.success(logService.query(sysLogDTO));
		 }
		 @PostMapping("/list")
		 public ApiResult getList(@RequestBody LogInput logInput){
		        SysLogDTO sysLogDTO=new SysLogDTO();
		        BeanUtils.copyProperties(logInput, sysLogDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysLogDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        return ApiResult.success(logService.queryList(sysLogDTO));
		 }
		 @PostMapping("/add")
		 public ApiResult add(@RequestBody LogInput logInput){
		        SysLogDTO sysLogDTO=new SysLogDTO();
		        BeanUtils.copyProperties(logInput, sysLogDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        //sysLogDTO.setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));
		        int cnt= logService.add(sysLogDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @PostMapping("/modify")
		 public ApiResult modify(@RequestBody LogInput logInput){
		        SysLogDTO sysLogDTO=new SysLogDTO();
		        BeanUtils.copyProperties(logInput, sysLogDTO);
		        //TODO 如果有日期类型，记得要转换一下！！！
		        sysLogDTO.setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));
		        int cnt= logService.modify(sysLogDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }
		 @GetMapping("/delete/{id}")
		 public ApiResult delete(@PathVariable("id") long id){
		        SysLogDTO sysLogDTO=new SysLogDTO();
		        sysLogDTO.setId(id); 
		        int cnt= logService.delete(sysLogDTO);
		        if(cnt==1){
		            return ApiResult.success(cnt);
		        }
		        return ApiResult.fail(BaseResultError.API_DO_FAIL);
		 }

}
