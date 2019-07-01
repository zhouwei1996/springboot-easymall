package com.jt.easymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.easymall.pojo.Product;
import com.jt.easymall.service.ProductService;
import com.jt.easymall.vo.EasyUIResult;
import com.jt.easymall.vo.Page;
import com.jt.easymall.vo.SysResult;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@RequestMapping("/product/page")
	public String queryProducts(
			Integer currentPage,Integer rows,Model model) throws Exception{
		List<Product> pList=productService.
			queryProducts(currentPage,rows);
		Integer totalPage=productService.queryTotalPage(rows);
		Page page=new Page();
		page.setProducts(pList);
		page.setCurrentPage(currentPage);
		page.setTotalPage(totalPage);
		model.addAttribute("page", page);
		return "product_list";
	}
	
	@RequestMapping("/product/findProductById/{id}")
	public String findProductById(@PathVariable String id,
			Model model) throws Exception{
		Product product=productService.findProductById(id);
		model.addAttribute("product",product);
		return "product_info";
		//{"category":"asdklfj","name":"dasklfh","price"}
	}
	
	//��̨�����߼�
	@RequestMapping("/product/save")
	@ResponseBody
	public SysResult saveProduct(Product product){
		SysResult result=new SysResult();
		try{
		int success=productService.saveProduct(product);
		if(success==1){
			//�ɹ�����һ��status=200��sysresult����
			result.setStatus(200);
			result.setMsg("�������Ʒ������");
			return result;
		}else{//ʧ����
			result.setStatus(201);
			result.setMsg(new String("����������ʲô����".getBytes(),"iso8859-1"));
			return result;}
		}catch(Exception e){
			result.setStatus(201);
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	//��̨����Ʒ��ҳ��ѯ
	@RequestMapping("/product/query")
	@ResponseBody
	public EasyUIResult queryManageProducts(
			Integer page,Integer rows) throws Exception{
	List<Product> pList=productService.queryProducts
			(page, rows);
	int total=productService.queryTotal();
	//��װ����list����,��������total��EasyUIResult��,��ajaxʹ��
	EasyUIResult result=new EasyUIResult();
	result.setTotal(total);
	result.setRows(pList);
	return result;
	}
	//��Ʒ�ĸ��²���
	/*����ʽ:post
	����url:/product/update
	�������:Product prduct
	��ǰ���е����б�ǩ���������л���jquery������ȡ
	productCategory=v1&productName=v2&productPrice=v3&productImgurl=v4&productNum=v5&productDescription=v6
	productId=wedrk231bnsdfk213kbr
	
	��ע:sql�����ν��и��²���
	update t_product set product_category=#{productCategory}...
	where product_id=#{productId}
	��������: SysResult�Ķ���json,200�ɹ�,����ʧ��*/
	@RequestMapping("/product/update")
	@ResponseBody
	public SysResult updateProduct(Product product){
		SysResult result=new SysResult();
		//����service���·���
		int success=productService.updateProduct(product);
		if(success==1){//�޸ĳɹ�
			result.setStatus(200);
			result.setMsg("ok");
			return result;
		}else{//ʧ����,ֻҪ����200���Ǳ�ʾʧ��
			result.setStatus(201);
			return result;
		}
	}
	
}




