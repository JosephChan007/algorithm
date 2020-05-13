package org.joseph.algorithm.lock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class LuaRedisLock {

    private JedisPool jedisPool;

    /**
     * 获取锁
     *
     * @param lockKey   锁的key
     * @param lockVal   锁的val，可以利用来实现"避免误删别人锁"、"锁的重入"等功能
     * @param lockTime  锁的最大生命时长，到期自动销毁，单位：毫秒
     * @param tryTime   等待获取锁的超时时间，单位：毫秒
     * @return
     */
    public Boolean tryLock(String lockKey, String lockVal, int lockTime, int tryTime) {

        //获取锁的开始时间
        Long tryBeginTime = System.currentTimeMillis();

        //轮询
        while (true) {

            String result = null;
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();

                // 原子性操作
                result = jedis.set(lockKey, lockVal, "NX", "PX", lockTime);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    try {
                        jedis.close();
                    } catch (Exception e) {
                    }
                }
            }

            //获取锁成功
            if ("ok".equals(result)) {
                return true;
            }

            //当前时间
            Long now = System.currentTimeMillis();
            //获取等待超时，就不用获取了
            if (now - tryBeginTime >= tryTime) {
                return false;
            }

            try {
                //阻塞等一会儿再重新去获取
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {

            }

        }
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param lockVal
     * @return
     */
    public void releaseLock(String lockKey, String lockVal) {

        //如果lockVal是自己的再删除，防止误删，场景来源：当前锁的持有者操作时间太长，锁已经自动释放并被别人占有了
        String lua = "if redis.call('get', KEYS[1]) == ARGV[1] then redis.call('del', KEYS[1]) end ";

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.eval(lua, Arrays.asList(lockKey), Arrays.asList(lockVal));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Exception e) {
                }
            }
        }
    }

    //测试
    public static void main(String[] args) {
        //连接池
        JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(), "127.0.0.1", 6379, 2000, "test123");
        LuaRedisLock simpleRedisLock = new LuaRedisLock();
        simpleRedisLock.jedisPool = jedisPool;

        //模拟10个并发
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                String lockKey = "TEST_LOCK_KEY";
                String threadName = Thread.currentThread().getName();

                //获取锁
                Boolean locked = simpleRedisLock.tryLock(lockKey, threadName, 30000, 5000);

                //获取锁失败
                if (!locked) {
                    System.err.println(">>> " + threadName + " 获取锁失败");
                    return;
                }

                //获取锁成功，模拟执行业务操作
                System.out.println(">>> " + threadName + " 获取锁成功");
                doShortBusiness();
                //doLongBusiness();

                //释放锁
                simpleRedisLock.releaseLock(lockKey, threadName);

            }).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(60000);
        } catch (InterruptedException e) {
        }
    }

    //短任务：100毫秒
    static void doShortBusiness() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    //长任务：3秒
    static void doLongBusiness() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
        }
    }



}
