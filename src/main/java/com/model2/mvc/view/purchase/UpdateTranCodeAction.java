package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action {

	// ��ǰ�� ���ſϷ� ���·� �ٲٴ� ��Ʈ�ѷ�
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// ���ǵ��� ��ư Ŭ����, �Ѿ���� ������
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode"); // ������ : 3
		System.out.println("tranNo: " + tranNo);
		System.out.println("tranCode: " + tranCode);

		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);
		purchase.setTranCode(tranCode);
		System.out.println("��Ʈ�ѷ������� " + purchase);

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updateTranCode(purchase);

		// ������������ �ӹ��� �����ϱ�..
		return "forward:/listPurchase.do";
	}

}
