package com.jt.easymall.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.jt.easymall.mapper.ProductMapper;
import com.jt.easymall.pojo.Product;
import com.jt.easymall.util.ObjectUtils;
import com.jt.easymall.util.RedisKeyPrefix;
import com.jt.easymall.util.UUIDUtil;

import redis.clients.jedis.JedisCluster;

@Service
public class ProductService{
	@Autowired
	private ProductMapper productMapper;
	
	/*@Autowired
	private RedisService redis;*/
	@Autowired
	private JedisCluster cluster;
	
	public List<Product> queryProducts(Integer currentPage, Integer rows) throws Exception {
		if(currentPage<=0){currentPage=1;}
		int start=(currentPage-1)*rows;
		String key=RedisKeyPrefix.PRODUCT_PAGE_PREFIX+currentPage+"_"+rows;
		//閸掋倖鏌囩�涙ê婀�
		if(cluster.exists(key)){//缂傛挸鐡ㄩ張澶嬫殶閹癸拷
			String listJson=cluster.get(key);//[{},{},{}]
			JsonNode data = ObjectUtils.MAPPER.readTree(listJson);
			if (data.isArray() && data.size() > 0) {
               List<Product> list = ObjectUtils.MAPPER.readValue(data.traverse(),
               ObjectUtils.MAPPER.getTypeFactory().constructCollectionType(List.class, Product.class));
               return list;
			}else{
				return null;
			}
		}else{
			List<Product> pList=productMapper.
					queryProducts(start,rows);
			String listJson=ObjectUtils.MAPPER.writeValueAsString(pList);
			cluster.set(key, listJson);
			return pList;
		}
	}
	
	public Integer queryTotalPage(Integer rows) {
		//閿熸枻鎷峰搧閿熸枻鎷烽敓鏉伴潻鎷烽敓鏂ゆ嫹,select count(*) from t_product
		int total=productMapper.queryCount();
		//閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓绲玱tal閿熸枻鎷穜ows閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽〉閿熸枻鎷穞otalPage
		int totalPage=total%rows==0?(total/rows):((total/rows)+1);
		return totalPage;
	}

	public Product findProductById(String id) throws Exception{
		//閸忋劌鐪懛顏勭暰娑斿鏁稉锟絢ey
		String key=RedisKeyPrefix.PRODUCT_PREFIX+id;
		//閸掋倖鏌囩�涙ê婀�
		if(cluster.exists(key)){//缂傛挸鐡ㄩ張澶嬫殶閹癸拷
			String productJson=cluster.get(key);
			//readValue鏉烆剙瀵查崡鏇氶嚋json鐎涙顑佹稉鎻掝嚠鎼存柨宕熸稉顏勵嚠鐠烇拷
			Product product=ObjectUtils.MAPPER.readValue(productJson, Product.class);
			return product;
		}else{
			Product product=productMapper.findProductById(id);
			String dataJson=ObjectUtils.MAPPER.writeValueAsString(product);
			cluster.set(key, dataJson);
			return product;
		}		
		
	}
	@Transactional
	public int saveProduct(Product product) {
		//缂洪敓鏂ゆ嫹id,浣块敓鏂ゆ嫹uuid閿熶茎鐧告嫹閿熸枻鎷烽敓鏂ゆ嫹
		String id=UUIDUtil.getUUID();
		product.setProductId(id);
		int success=productMapper.saveProduct(product);
		//int a=1/0;
		return success;
	}

	public int queryTotal() {
		return productMapper.queryCount();
	}

	public int updateProduct(Product product) {
		int success=productMapper.updateProduct(product);
		return success;
	}

}
