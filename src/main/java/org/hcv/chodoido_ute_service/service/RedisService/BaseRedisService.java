package org.hcv.chodoido_ute_service.service.RedisService;

import org.hcv.chodoido_ute_service.applicationInitConfig.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class BaseRedisService implements IBaseRedisService{
    private final RedisConfig redisConfig;

    @Autowired
    public BaseRedisService(RedisConfig redisConfig){
        this.redisConfig = redisConfig;
    }

    @Override
    public void save(String key, Object value) {
        redisConfig.redisTemplate().opsForValue().set(key, value);
    }
    @Override
    public void save(String token, String email, long l, TimeUnit timeUnit) {
        redisConfig.redisTemplate().opsForValue().set(token, email, l, timeUnit);
    }

    @Override
    public Object get(String key) {
        return redisConfig.redisTemplate().opsForValue().get(key);
    }

    @Override
    public HashMap<String, Object> gets() {
        HashMap<String, Object> listRedis = new HashMap<>();
        Set<String> keys = redisConfig.redisTemplate().keys("*");
        assert keys != null;
        keys.forEach(key -> {
            listRedis.put(key, redisConfig.redisTemplate().opsForValue().get(key));
        });
        return listRedis;
    }

    @Override
    public void delete(String key) {
        redisConfig.redisTemplate().delete(key);
    }

    @Override
    public boolean isExists(String key) {
        return redisConfig.redisTemplate().opsForValue().get(key) != null;
    }


}
