package com.zxk.study.controller;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zxk.study.module.SysUserDTO;
import com.zxk.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhouxinkai
 * @Description: sentinel流控和熔断
 * 流控一般设置在服务提供端
 * 熔断一般设置在服务调用端
 * @date 2022/5/15  11:15
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     *把需要控制流量的代码用 Sentinel API SphU.entry("HelloWorld") 和 entry.exit() 包围起来即可
     * 缺点：侵入性太强
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public String getDetail(@RequestParam("id") long id) {
        Entry entry = null;
        try {
            //1.定义资源，sentinel是对资源进行限定的
            entry = SphU.entry("hello");
            //被保护的业务逻辑，也就是被限流的业务逻辑
            SysUserDTO sysUserDTO = new SysUserDTO();
            sysUserDTO.setId(id);
            SysUserDTO userDTO = userService.query(sysUserDTO);
            return "未被限流"+userDTO.toString();
        } catch (BlockException e1) {
            // 2.资源访问阻止，被限流或被降级
            // 进行相应的处理操作
            log.debug("get被流控了");
            return "被限流了";
        } catch (Exception e2) {
            // 3.若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e2, entry);
            return "";
        } finally {
            // 4.务必保证 exit，务必保证每个 entry 与 exit 配对
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * 使用@SentinelResource进行流控
     * 前置条件：1、导入依赖<groupId>com.alibaba.csp</groupId>
     *                  <artifactId>sentinel-annotation-aspectj</artifactId>
     *         2、配置一个支持@SentinelResource的bean
     *                  @Configuration
     *                  public class SentinelAspectConfig {
     *                  @Bean
     *                  public SentinelResourceAspect sentinelResourceAspect(){
     *                  return new SentinelResourceAspect();
     *                  }
     *                  }
     *         3、SentinelResource参数详解
     *                  value：资源名称，必需项（不能为空）
     *                  entryType：entry 类型，可选项（默认为 EntryType.OUT）
     *                  blockHandler / blockHandlerClass:
     *                      blockHandler 对应处理 BlockException 的函数名称，可选项。
     *                      blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
     *                      blockHandler 函数默认需要和原方法在同一个类中。
     *                          若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *                  fallback / fallbackClass：
     *                      fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。
     *                      fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。
     *                      fallback 函数签名和位置要求：
     *                          返回值类型必须与原函数返回值类型一致；
     *                          方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     *                      fallback 函数默认需要和原方法在同一个类中。
     *                          若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *                  defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
     *                      返回值类型必须与原函数返回值类型一致；
     *                      方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     *                      defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *                  exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。
     *          4、设置限流规则
     *
     * @return
     */
    @GetMapping("/test1")
    @SentinelResource(value = "test1",blockHandler = "blockHandlerForTest1",fallback = "fallbackForTest1")
    public String test1() throws Exception {
        throw new Exception("");
        //return "Hello，正常访问";
    }

    /**
     * SentinelResource中的blockHandler指定的方法
     * 以下是BlockException的实现子类
     *  AuthorityException
     *  DegradeException
     *  FlowException
     *  SystemBlockException
     * 所以我现在知道不论是流控还是降级，都可以在这里面进行后续的逻辑处理
     * @return
     */
    public String blockHandlerForTest1(BlockException e) {
        //判断是否是被限流了
        if (e instanceof FlowException) {
            return "被限流了，重新试一下";
        }
        if (e instanceof DegradeException){
            return "被熔断了，10之后重试";
        }
        return "暂时不写";
    }

    /**
     * SentinelResource中fallback指定的方法
     * 用于处理异常，除了BlockException
     * @return
     */
    public String fallbackForTest1(){
        return "出异常了，请重试";
    }
    /**
     * 流控规则的重要属性：
     * Field	说明	/默认值(/前面是参数说明，后面是默认值)
     * resource	资源名，资源名是限流规则的作用对象
     * count	限流阈值
     * grade	限流阈值类型，QPS 模式（1）或并发线程数模式（0）	/QPS 模式
     * limitApp	流控针对的调用来源	    /default，代表不区分调用来源
     * strategy	调用关系限流策略：直接、链路、关联	    /根据资源本身（直接）
     * controlBehavior	流控效果（直接拒绝/WarmUp/匀速+排队等待），不支持按调用关系限流	/直接拒绝
     * clusterMode	是否集群限流	    /否
     */
    @PostConstruct
    private void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        //5.一个FlowRule就是一个流控规则
        FlowRule rule = new FlowRule();
        //6.设置流控规则对应的流控资源
        rule.setResource("hello");
        //7.设置流控规则的模式
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        //8.设置访问阈值
        // set limit qps to 20
        rule.setCount(1);
        rules.add(rule);

        FlowRule ruleForTest1 = new FlowRule();
        ruleForTest1.setResource("test1");
        ruleForTest1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        ruleForTest1.setCount(1);
        rules.add(ruleForTest1);
        //9.最后加载流控规则
        FlowRuleManager.loadRules(rules);
    }

    @PostConstruct
    private void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("test1");
        // set threshold RT, 10 ms
        rule.setCount(2);
        rule.setMinRequestAmount(2);
        rule.setStatIntervalMs(60*1000);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
    /**
     * 使用Sentinel控制台进行服务流控和熔断等操作步骤如下：
     * （以下步骤来自springcloud alibaba）
     * 1、下载Sentinel控制台的jar包
     * 2、java -Dserver.port=8080 -jar sentinel-dashboard.jar 运行jar包
     * 3、访问localhost:8080
     * 4、客户端接入控制台
     * 4.1 引入JAR包
     * <dependency>
     *     <groupId>com.alibaba.csp</groupId>
     *     <artifactId>sentinel-transport-simple-http</artifactId>
     *     <version>x.y.z</version>
     * </dependency>
     * 4.2 配置启动参数
     *  启动时加入 JVM 参数 -Dcsp.sentinel.dashboard.server=consoleIp:port 指定控制台地址和端口。
     *  若启动多个应用，则需要通过 -Dcsp.sentinel.api.port=xxxx 指定客户端监控 API 的端口（默认是 8719）。
     * 从 1.6.3 版本开始，控制台支持网关流控规则管理。
     * 您需要在接入端添加 -Dcsp.sentinel.app.type=1 启动参数以将您的服务标记为 API Gateway，在接入控制台时您的服务会自动注册为网关类型，然后您即可在控制台配置网关规则和 API 分组。
     * 除了修改 JVM 参数，也可以通过配置文件取得同样的效果。更详细的信息可以参考 启动配置项。
     * 4.3 触发客户端初始化
     * 确保客户端有访问量，Sentinel 会在客户端首次调用的时候进行初始化，开始向控制台发送心跳包。
     * 注意：您还需要根据您的应用类型和接入方式引入对应的 适配依赖，否则即使有访问量也不能被 Sentinel 统计。
     */
}