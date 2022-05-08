-- 使用Lua脚本实现可重入锁加锁
--额外加入可重试次数
-- 定义锁的key
local key = KEYS[1];
--定义线程的唯一标识
local threadId = ARGV[1];
--定义超时时间
local outTime = ARGV[2];
--定义最大重试次数
local maxTime = ARGV[3];

--最外层用循环进行尝试
while(true)
do
    --定义当前尝试次数初值为0
    local currentTime = 0;
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
    --执行到这里说明此次加锁失败，当前尝试加锁次数+1
    currentTime = currentTime+1
    --判断是否超过重试次数
    if (currentTime > maxTime) then
        --超过规定最大重试次数，就不再重试,跳出循环
        break;
    end
    --否则还会继续重试
end;
--执行到这里说明此次加锁失败
return 0;