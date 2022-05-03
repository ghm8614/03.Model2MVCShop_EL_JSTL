package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddPurchaseViewAction extends Action {

	// ������ ��ǰ ���� �������� (getProduct(prod_no))
	// ��ǰ����ȸ -> ���Ź�ư -> AddPurchaseView.do?prod_no=10021
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prod_no"));
		
		// prod_no �� getProduct(prod_no) ȭ�鿡 ������� os�� ����
		ProductService productService = new ProductServiceImpl();
		Product product = productService.findProduct(prodNo);
		
		request.setAttribute("product", product);
		
		// ������ �������� �����ϹǷ�, addPurchaseView.jsp X
		return "forward:/purchase/addPurchase.jsp";
	}

}
