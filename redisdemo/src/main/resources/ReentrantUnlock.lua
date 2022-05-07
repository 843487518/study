-- 使用Lua脚本实现可重入锁解锁
-- 定义锁的key
local key = KEYS[1];
--定义线程的唯一标识
local threadId = ARGV[1];
--定义超时时间
local outTime = ARGV[2];

--判断锁是否是当前线程持有的
if (redis.call('HEXISTS',key,threadId) == 0) then
    --不是当前线程加的锁，不管
    return 0;
end;
--执行到这里说明锁是当前线程持有的
--重入次数-1,并得到结果
local count = redis.call('HINCRBY',key,threadId,'-1');
--判断重入次数
if (count > 0) then
    --count>0，不能释放锁，更新有效时间
    redis.call('EXPIRE',key,outTime);
    return 1;
else
    --等于0说明可以直接释放锁
    redis.call('DEL',key);
    return 1;
end;