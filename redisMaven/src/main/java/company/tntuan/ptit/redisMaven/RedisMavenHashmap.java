package company.tntuan.ptit.redisMaven;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RedisMavenHashmap {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
//        jedis.auth("tntuan");
        Map<String,String> test = new HashMap<String, String>();
        for (int i = 1; i <= 5; i++) {
            test.put(i+"", (int) Math.pow(i,2) +"");
        }
        
        jedis.hmset("test", test);

//        Set<Map.Entry<Integer, Integer>> entry = test.entrySet();
//        for (Map.Entry<Integer, Integer> i: entry) {
//            jedis.hset("test",i.getKey().toString(),i.getValue().toString());
//        }
        System.out.println("Add Hashmap into redis successfully!!!");
    }
}
