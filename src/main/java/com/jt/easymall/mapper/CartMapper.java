package com.jt.easymall.mapper;

import java.util.List;

import com.jt.easymall.pojo.Cart;

public interface CartMapper {

	List<Cart> queryMycart(String userId);

	Cart findOne(Cart _cart);

	int addCart(Cart _cart);

	int updateCartNum(Cart exists);

}
