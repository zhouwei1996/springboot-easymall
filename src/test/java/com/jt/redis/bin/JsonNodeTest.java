package com.jt.redis.bin;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.jt.easymall.pojo.User;
import com.jt.easymall.util.ObjectUtils;

public class JsonNodeTest {
	
	@Test
	public void test() throws Exception{
		User user=new User();
		user.setUserEmail("asdf");
		user.setUserId("sdafas");
		user.setUserName("sadfasd");
		user.setUserNickname("asdfasd");
		user.setUserPassword("asdf9697ni");
		//[{"userId":"asdfsda"},{},{}]
		String userJson=ObjectUtils.MAPPER.writeValueAsString(user);
		//褰撳墠绯荤粺鏈繀鏈塲son瀛楃涓插搴旂殑pojo绫�
		//鏃犺json瀛楃涓叉槸鍗曚釜瀵硅薄,杩樻槸list涓�缁�,閮藉彲浠ヨВ鏋愭垚jsonNode
		//jsonNode搴曞眰灏辨槸map+闆嗗悎
		JsonNode data=ObjectUtils.MAPPER.readTree(userJson);
		//灏变粠jsonNode涓幏鍙杣serId
		System.out.println(data.get("userId").asText());
		int a=1;
	
	}
	
	public void test1(){
		
	}
}
