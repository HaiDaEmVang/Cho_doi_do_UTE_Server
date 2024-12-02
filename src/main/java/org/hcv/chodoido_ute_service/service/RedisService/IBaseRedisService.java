package org.hcv.chodoido_ute_service.service.RedisService;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public interface IBaseRedisService {
    void save(String key, Object value);
    void save(String token, String email, long l, TimeUnit timeUnit);
    Object get(String key);
    HashMap<String, Object> gets();
    void delete(String key);
    boolean isExists(String key);
}
