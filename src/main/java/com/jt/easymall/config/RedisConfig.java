package com.jt.easymall.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

//@Configuration
//@ConfigurationProperties(prefix="spring.redis")
public class RedisConfig {
	@Value("${spring.redis.nodes}")
	private String nodes;
	@Value("${spring.redis.maxTotal}")
	private Integer maxTotal;
	@Value("${spring.redis.minIdle}")
	private Integer minIdle;
	@Value("${spring.redis.maxIdle}")
	private Integer maxIdle;
	
	//閺嬪嫰锟界姾绻涢幒銉︾潨鐎电钖�
	@Bean
	public ShardedJedisPool getPool(){
		//閺�鍫曟肠閼哄倻鍋ｆ穱鈩冧紖
		List<JedisShardInfo> infoList=new ArrayList<JedisShardInfo>();
		//閹搭亜褰�,鐏忓攳odes閸掆晝鏁�"," 閹搭亜褰囬幋鎰礋娑擃亞娈戞潻鐐村复閸滃瞼顏崣锟�
		String[] node = nodes.split(",");//{"10.9.9.9:6379","",""}
		for (String hostAndPort : node) {
			//"10.9.9.9:6379"
			String host=hostAndPort.split(":")[0];
			Integer port=Integer.parseInt(hostAndPort.split(":")[1]);
			infoList.add(new JedisShardInfo(host,port));
		}
		//闁板秶鐤哻onfig
		JedisPoolConfig config =new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		ShardedJedisPool pool=new ShardedJedisPool(config,infoList);
		return pool;
	}
}



















