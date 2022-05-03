package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeByProdAction extends Action {

	// admin ���� ��ǰ����(listProduct)���� ����ϱ� ��ư
	// ����ϱ� ��ư Ŭ�� -> ��ǰ���� listProduct ������� : �����
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ����ϱ� ��ư Ŭ����, �Ѿ���� ������
		String prodNo = request.getParameter("prodNo");
		String tranCode = request.getParameter("tranCode"); // ������ : 2

		Product product = new Product();
		product.setProdNo(Integer.parseInt(prodNo));

		Purchase purchase = new Purchase();
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		System.out.println("��Ʈ�ѷ������� " + purchase);

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updateTranCode(purchase);
		
		// listProduct.jsp
		return "forward:/listProduct.do?menu=manage";
	}

}
