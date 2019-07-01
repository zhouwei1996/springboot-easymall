package com.jt.redis.bin;

import org.junit.Test;

public class BinTest {
	
	
	@Test
	public void test(){
		byte a=55;
		//假设,这个byte二进制代表位序列中第一个byte
		System.out.println(Integer.toBinaryString(a));
		//获取定义的0号下标对应的二进制
		for(int i=0;i<8;i++){
			//第一次移动7位,第二次移动6,第三次移动5
			int move=8-i-1;
			int result=(a>>move)&1;
			System.out.println("当前"+i+"号下标对应的二进制"+result);
		}
		
	}
}
