package com.zxk.study.controller;

import com.zxk.study.utils.Student;
import com.zxk.study.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/5/7  11:25
 */
@Controller
@RequestMapping("/v1/redis/ops")
public class RedisController {
    /**
     * 导入Redis操作类StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 针对String类型的value进行操作
     */
    @SneakyThrows
    @GetMapping("/forstring")
    public void opsString(){

        //添加String类型,并设置过期时间
        stringRedisTemplate.opsForValue().set("ops:string:2","love",100, TimeUnit.MINUTES);
        stringRedisTemplate.opsForValue().set("ops:string:1","love");

        //当key不存在时才添加
        stringRedisTemplate.opsForValue().setIfAbsent("ops:string:1","love???");
        stringRedisTemplate.opsForValue().setIfAbsent("ops:string:3","love???");

        //创建一个map来存储要添加的key和value
        Map<String,String> map = new TreeMap<>();
        map.put("ops:string:mapkey1","ops:string:mapvalue1");
        map.put("ops:string:mapkey2","ops:string:mapvalue2");
        map.put("ops:string:mapkey3","ops:string:mapvalue3");
        //批量添加
        stringRedisTemplate.opsForValue().multiSet(map);

        //通过key获取
        stringRedisTemplate.opsForValue().get("ops:string:1");
        /**
         * RedisTemplate的公有方法
         */
        //删除指定的元素
        stringRedisTemplate.delete("ops:string:mapkey1");
        //指定过期时间
        stringRedisTemplate.expire("ops:string:3",2000,TimeUnit.MINUTES);
        //判断key是否存在
        Boolean aBoolean = stringRedisTemplate.hasKey("ops:string:3");
        System.out.println(aBoolean);
        //获取key的过期时间
        Long expire = stringRedisTemplate.getExpire("ops:string:3");
        System.out.println(expire);


        //添加数值类型
        stringRedisTemplate.opsForValue().set("ops:string:21","10");
        //自增和自减
        System.out.println(stringRedisTemplate.opsForValue().increment("ops:string:21"));
        System.out.println(stringRedisTemplate.opsForValue().increment("ops:string:21",3));
        System.out.println(stringRedisTemplate.opsForValue().increment("ops:string:21",-2));

        //存储对象,业务中就行序列化，不依靠Redis进行
        Student student = new Student(1L,"Tom",20);
        String s = Utils.jsonMapper.writeValueAsString(student);
        System.out.println(s);
        stringRedisTemplate.opsForValue().set("ops:string:31",s);
        String s1 = stringRedisTemplate.opsForValue().get("ops:string:31");
        System.out.println(s1);
        Student student1 = Utils.jsonMapper.readValue(s1, Student.class);
        System.out.println(student1);

    }

    /**
     * 针对Hash类型的value进行操作
     */
    @GetMapping("/forhash")
    public void opsHash(){
        //添加单个元素
        stringRedisTemplate.opsForHash().put("ops:hash:1","name","jack");
        stringRedisTemplate.opsForHash().put("ops:hash:1","age","23");

        //通过map添加多个元素
        Map<String, String> map = new TreeMap<>();
        map.put("name","john");
        map.put("age","18");
        stringRedisTemplate.opsForHash().putAll("ops:hash:2",map);

        //获取key对应的所有值（hashkey+value）
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("ops:hash:2");
        System.out.println(entries);
        List<Object> values = stringRedisTemplate.opsForHash().values("ops:hash:2");
        System.out.println(values);
        //获取单个值
        System.out.println(stringRedisTemplate.opsForHash().get("ops:hash:2","name"));
        //判断是否存在
        stringRedisTemplate.opsForHash().hasKey("ops:hash:2","name");
    }

    /**
     * 针对List类型的value进行操作
     * 类似于LinkedList，双向链表
     */
    @GetMapping("/forlist")
    public void opsList(){
        //添加
        stringRedisTemplate.opsForList().set("ops:list:1",0,"v0");
        //获取指定位置的元素
        String index = stringRedisTemplate.opsForList().index("ops:list:1", 0);
        System.out.println(index);
        //获取指定元素的位置
        Long v0 = stringRedisTemplate.opsForList().indexOf("ops:list:1", "v0");
        System.out.println(v0);
        //添加多个元素
        stringRedisTemplate.opsForList().leftPushAll("ops:list:1","v1","v2","v3");
        //从右端删除元素
        String s = stringRedisTemplate.opsForList().rightPop("ops:list:1");
        System.out.println(s);
        String s1 = stringRedisTemplate.opsForList().rightPop("ops:list:1");
        System.out.println(s1);
    }

    /**
     * 针对set类型的value进行操作
     * 无序、不可重复
     */
    @GetMapping("/forset")
    public void opsset(){
        //添加元素
        stringRedisTemplate.opsForSet().add("ops:set:1","s1","s2","s3");
        //删除元素
        stringRedisTemplate.opsForSet().remove("ops:set:1","s2");
        //判断是否存在
        Boolean s1 = stringRedisTemplate.opsForSet().isMember("ops:set:1", "s1");
        System.out.println(s1);
        //获取所有的元素
        Set<String> members = stringRedisTemplate.opsForSet().members("ops:set:1");
        System.out.println(members);

    }


    @GetMapping("/forzset")
    public void opszset(){
        //添加一个元素
        stringRedisTemplate.opsForZSet().add("ops:zset:1","z1",1);
        stringRedisTemplate.opsForZSet().add("ops:zset:1","z2",2);
        stringRedisTemplate.opsForZSet().add("ops:zset:1","z3",3);

        //获取元素的序号
        stringRedisTemplate.opsForZSet().score("ops:zset:1","z1");
        //获取序号最大的
        stringRedisTemplate.opsForZSet().popMax("ops:zset:1");
        //获取序号最小的
        stringRedisTemplate.opsForZSet().popMin("ops:zset:1");
    }
}
