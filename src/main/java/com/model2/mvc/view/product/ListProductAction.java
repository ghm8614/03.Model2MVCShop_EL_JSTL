package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

//@WebServlet("/ListProductAction")
public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String menu = request.getParameter("menu");
		System.out.println("menu: " + menu);
		// ==================================================================

		Search search = new Search();

		// �ǸŻ�ǰ���� Ŭ���� �ʱ������� 1
		int currentPage = 1;

		// page ��ư Ŭ����, listProduct.do
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		// search�� ���������� ���� �־���ϳ�??
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));

		int pageSize = Integer.parseInt(super.getServletContext().getInitParameter("pageSize"));
		search.setPageSize(pageSize);

		System.out.println("��Ʈ�ѷ������� " + search);
		// ==================================================================

		ProductService productService = new ProductServiceImpl();
		Map<String, Object> map = productService.getProductList(search);

		int pageUnit = Integer.parseInt(this.getServletContext().getInitParameter("pageUnit"));
		Page resultPage = new Page(currentPage, ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListProductAction : " + resultPage);

		
		// map1 : (String "count", Integer total)
		// map2 : (String "list", ArrayList<ProductVO> list)
		request.setAttribute("menu", menu);
		request.setAttribute("search", search);
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("list", map.get("list"));

		return "forward:/product/listProduct.jsp";
	}
}
