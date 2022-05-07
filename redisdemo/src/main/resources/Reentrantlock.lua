-- 使用Lua脚本实现可重入锁加锁
-- 定义锁的key
local key = KEYS[1];
--定义线程的唯一标识
local threadId = ARGV[1];
--定义超时时间
local outTime = ARGV[2];

--判断key是否存在，也就是判断锁是否已经存在
if (redis.call('exists',key) == 0) then
    --不存在的情况下，获取锁，也就是插入数据
    redis.call('hset',key,threadId,'1');
    --设置有效期
    redis.call('expire',key,outTime);
    --加锁成功，返回结果
    return 1;
end;
--执行到这里说明锁已经存在了
--判断threadId是否一样，也就是判断是否是当前线程加的锁
if (redis.call('hexists',key,threadId) == 1) then
    --也就是当前线程加的锁，重入次数+1
    redis.call('hincrby',key,threadId,'1');
    --刷新有效时间
    redis.call('expire',key,outTime);
    --加锁成功，返回结果
    return 1;
end;
--执行到这里说明所已经存在，但是不是自己的，加锁失败
return 0;