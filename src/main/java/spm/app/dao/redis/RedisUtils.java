package spm.app.dao.redis;

import org.apdplat.word.segmentation.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtils {
    /**
     * 操作Redis的工具类
     */
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate rt;

    /**
     * 接收map 存入指定的Key 没有就新建 有就累加
     */
    public int putHashWithList(String key, List<Word> wordList, Long time) {
        for (Word w : wordList) {
            putHashWithKV(key, w.getText(), 1L, time);
        }
        return wordList.size();
    }

    /**
     * 接收键值对 存入指定的Key 没有就新建 有就累加 指定过期时间
     */
    public void putHashWithKV(String key, String mkey, Long value, Long time) {
        BoundHashOperations bo = rt.boundHashOps(key);
        bo.increment(mkey, value);
        bo.expire(time, TimeUnit.MINUTES);
    }

    /**
     * 查找指定Key下的键值
     *
     * @return
     */
    public Object findHash(String key, String mkey) {
        return rt.boundHashOps(key).get(mkey);
    }
}
