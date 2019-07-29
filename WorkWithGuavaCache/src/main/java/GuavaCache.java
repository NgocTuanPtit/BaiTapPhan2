import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class GuavaCache {
public static int i = 0;
    private LoadingCache<Integer,Integer> cache;
    GuavaCache(){
        cache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build(new CacheLoader<Integer, Integer>() {
            @Override
            public Integer load(Integer param) throws Exception {
                System.out.println("access load");
                Integer value = param*param;
                if(cache.asMap().containsKey(param)){
                    return cache.asMap().get(param);
                }
                else{
                    cache.asMap().put(param, value);
                    return value;
                }
            }
        });
    }

    public synchronized Integer getParam(Integer param){
        Integer result = null;
        try {
            result = cache.get(param);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

}
