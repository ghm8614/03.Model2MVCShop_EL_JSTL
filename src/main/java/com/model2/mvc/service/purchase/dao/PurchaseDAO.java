package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDAO {

	// C
	public PurchaseDAO() {

	}

	// M
	//
	public void insertPurchase(Purchase purchase) throws Exception {

		System.out.println("======================================");
		System.out.println("insertPurchase");
		System.out.println(purchase);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "INSERT INTO transaction VALUES(seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate,?)";

		System.out.println("2. 쿼리객체 생성");
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		pstmt.setString(2, purchase.getBuyer().getUserId());
		pstmt.setString(3, purchase.getPaymentOption());
		pstmt.setString(4, purchase.getReceiverName());
		pstmt.setString(5, purchase.getReceiverPhone());
		pstmt.setString(6, purchase.getDivyAddr());
		pstmt.setString(7, purchase.getDivyRequest());
		pstmt.setString(8, purchase.getTranCode());
		pstmt.setString(9, purchase.getDivyDate());

		System.out.println("3. 쿼리 전송");
		int result = pstmt.executeUpdate();

		if (result == 1) {
			System.out.println("4. 쿼리 전송 성공");
		}
		System.out.println("======================================");

		pstmt.close();
		con.close();

	}

	public Purchase findPurchase(int tranNo) throws Exception {

		System.out.println("====================================");
		System.out.println("findPurchase");
		System.out.println("tranNo: " + tranNo);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "SELECT * FROM transaction WHERE tran_no = ?";
		System.out.println("2. 쿼리객체 생성");
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, tranNo);

		ResultSet rs = pstmt.executeQuery();
		System.out.println("3. 쿼리 db로 보내고 받아오기");

		Purchase purchase = null;
		while (rs.next()) {
			/*
			 * System.out.println(rs.getInt("tran_no"));
			 * System.out.println(rs.getInt("prod_no"));
			 * System.out.println(rs.getString("buyer_id"));
			 * System.out.println(rs.getString("payment_option") + "A");
			 * System.out.println(rs.getString("receiver_name"));
			 * System.out.println(rs.getString("receiver_phone"));
			 * System.out.println(rs.getString("demailaddr"));
			 * System.out.println(rs.getString("dlvy_request"));
			 * System.out.println(rs.getString("tran_status_code") + "A");
			 * System.out.println(rs.getString("order_data"));
			 * System.out.println(rs.getString("dlvy_date"));
			 */

			purchase = new Purchase();

			Product product = new Product();
			product.setProdNo(rs.getInt("prod_no"));

			User user = new User();
			user.setUserId(rs.getString("buyer_id"));

			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);

			purchase.setTranNo(rs.getInt("tran_no"));
			purchase.setPaymentOption(rs.getString("payment_option").trim());
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setDivyAddr(rs.getString("demailaddr"));
			purchase.setDivyRequest(rs.getString("dlvy_request"));
			purchase.setTranCode(rs.getString("tran_status_code").trim());
			purchase.setOrderDate(rs.getDate("order_data"));
			purchase.setDivyDate(rs.getString("dlvy_date"));

			System.out.println(("4. data 받아오기 성공"));
		}
		System.out.println("dao에서의 " + purchase);

		pstmt.close();
		con.close();

		return purchase;
	}

	public HashMap<String, Object> getSaleList(Search search) {

		return null;
	}

	// update
	public void updatePurchase(Purchase purchase) throws Exception {

		System.out.println("====================================");
		System.out.println("updatePurchase");
		System.out.println("dao에서의 " + purchase);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "UPDATE transaction \r\n"
				+ "SET payment_option=?, receiver_name=?, receiver_phone=?, demailaddr=?, dlvy_request=?, dlvy_date=? \r\n"
				+ "WHERE tran_no = ?";

		System.out.println("2. 쿼리객체 생성");
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, purchase.getPaymentOption());
		pstmt.setString(2, purchase.getReceiverName());
		pstmt.setString(3, purchase.getReceiverPhone());
		pstmt.setString(4, purchase.getDivyAddr());
		pstmt.setString(5, purchase.getDivyRequest());
		pstmt.setString(6, purchase.getDivyDate());
		pstmt.setInt(7, purchase.getTranNo());

		System.out.println("3. 쿼리 전송");
		int result = pstmt.executeUpdate();

		if (result == 1) {
			System.out.println("4. 쿼리 전송 성공");
		}

		pstmt.close();
		con.close();

	}

	/*
	 * public Map<String, Object> getPurchaseList(Search search, String userId)
	 * throws Exception {
	 * 
	 * System.out.println("======================================");
	 * System.out.println("getPurchaseList(Search search)");
	 * System.out.println("dao에서의 " + search); System.out.println("dao에서의 userId:  "
	 * + userId);
	 * 
	 * Connection con = DBUtil.getConnection();
	 * System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");
	 * 
	 * String sql =
	 * "SELECT T.tran_no, T.order_data, P.prod_no, P.prod_name, T.receiver_name, T.demailaddr, T.tran_status_code, T.payment_option\r\n"
	 * +
	 * "FROM transaction T, product P WHERE P.prod_no = T.prod_no AND buyer_id = ? "
	 * ;
	 * 
	 * System.out.println("2. 쿼리객체 생성"); PreparedStatement pstmt =
	 * con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_UPDATABLE); pstmt.setString(1, userId);
	 * 
	 * ResultSet rs = pstmt.executeQuery();
	 * System.out.println("3. 쿼리 db로 보내고 받아오기");
	 * 
	 * rs.last(); // 커서를 맨 마지막 row로 이동 int total = rs.getRow(); // 현재 커서가 가리키는 row
	 * 번호 get System.out.println("로우의 수:" + total);
	 * 
	 * Map<String, Object> map = new HashMap<>(); map.put("count", new
	 * Integer(total));
	 * 
	 * rs.absolute(search.getCurrentPage() * search.getPageSize() -
	 * search.getPageSize() + 1);
	 * 
	 * List<Purchase> list = new ArrayList<>();
	 * 
	 * if (total > 0) {
	 * 
	 * for (int i = 0; i < search.getPageSize(); i++) {
	 * 
	 * System.out.println("주문번호:" + rs.getString("tran_no"));
	 * System.out.println("주문일자:" + rs.getString("order_data"));
	 * System.out.println("상품번호:" + rs.getString("prod_no"));
	 * System.out.println("상품명:" + rs.getString("prod_name"));
	 * System.out.println("수령인:" + rs.getString("receiver_name"));
	 * System.out.println("배송주소:" + rs.getString("demailaddr"));
	 * System.out.println("상태코드:" + rs.getString("tran_status_code"));
	 * System.out.println("결제방법:" + rs.getString("payment_option"));
	 * 
	 * Purchase purchase = new Purchase(); Product product = new Product();
	 * 
	 * product.setProdNo(rs.getInt("prod_no"));
	 * product.setProdName(rs.getString("prod_name"));
	 * 
	 * purchase.setPurchaseProd(product);
	 * 
	 * purchase.setTranNo(rs.getInt("tran_no"));
	 * purchase.setOrderDate(rs.getDate("order_data"));
	 * purchase.setReceiverName(rs.getString("receiver_name"));
	 * purchase.setDivyAddr(rs.getString("demailaddr"));
	 * purchase.setTranCode(rs.getString("tran_status_code").trim());
	 * purchase.setPaymentOption(rs.getString("payment_option").trim());
	 * System.out.println(("4. data 받아오기 성공"));
	 * 
	 * list.add(purchase);
	 * 
	 * if (!rs.next()) { break; } }
	 * 
	 * map.put("list", list);
	 * 
	 * pstmt.close(); con.close(); }
	 * 
	 * return map; }
	 */

	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "SELECT T.tran_no, T.order_data, P.prod_no, P.prod_name, T.receiver_name, T.demailaddr, T.tran_status_code, T.payment_option\r\n"
				+ "FROM transaction T, product P WHERE P.prod_no = T.prod_no AND buyer_id = " + "'"+userId+"'";

		// db에서 꺼내온 data의 전체 recode 수
		int totalCount = this.getTotalCount(sql);

		//
		sql = makeCurrentPageSql(sql, search);

		System.out.println("2. 쿼리객체 생성");
		// 쿼리를 db에 질의하고 값을 ResultSet 객체로 전달받음
		PreparedStatement pstmt = con.prepareStatement(sql);

		System.out.println("3. 쿼리 전송 및 data 가져오기");
		ResultSet rs = pstmt.executeQuery();

		List<Purchase> list = new ArrayList<Purchase>();

		while (rs.next()) {

			Purchase purchase = new Purchase();
			Product product = new Product();

			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("prod_name"));
			purchase.setPurchaseProd(product);

			purchase.setTranNo(rs.getInt("tran_no"));
			purchase.setOrderDate(rs.getDate("order_data"));
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setDivyAddr(rs.getString("demailaddr"));
			purchase.setTranCode(rs.getString("tran_status_code").trim());
			purchase.setPaymentOption(rs.getString("payment_option").trim());

//			product.setProdNo(rs.getInt("PROD_NO"));
//			product.setProdName(rs.getString("PROD_NAME"));
//			product.setProdDetail(rs.getString("PROD_DETAIL"));
//			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
//			product.setPrice(rs.getInt("PRICE"));
//			product.setFileName(rs.getString("IMAGE_FILE"));
//			product.setRegDate(rs.getDate("REG_DATE"));
//			product.setProTranCode(rs.getString("tran_status_code")); // null : 판매중, 1 : 구매완료

			list.add(purchase);
			System.out.println("4. data 가져오기 성공");
		}
		// totalCount
		map.put("totalCount", new Integer(totalCount));

		map.put("list", list);
		// map.put("userId", userId);

		System.out.println("======================================");

		rs.close();
		pstmt.close();
		con.close();

		return map;
	}

	private int getTotalCount(String sql) throws Exception {

		sql = "SELECT COUNT(*) FROM (" + sql + ")";

		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		int totalCount = 0;
		if (rs.next()) {
			totalCount = rs.getInt(1);
		}

		rs.close();
		pstmt.close();
		con.close();

		return totalCount;
	}

	private String makeCurrentPageSql(String sql, Search search) {

		sql = "SELECT * " + "FROM (		SELECT vt. * ,  ROWNUM AS row_seq " + " 	FROM (	" + sql + " ) vt "
				+ "	WHERE ROWNUM <=" + search.getCurrentPage() * search.getPageSize() + " ) " + "WHERE row_seq BETWEEN "
				+ ((search.getCurrentPage() - 1) * search.getPageSize() + 1) + " AND "
				+ search.getCurrentPage() * search.getPageSize();

		System.out.println("PurchaseDAO :: make SQL :: " + sql);

		return sql;
	}

	public void updateTranCode(Purchase purchase) throws Exception {

		System.out.println("====================================");
		System.out.println("updateTranCode");
		System.out.println("dao에서의 " + purchase);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

//		String sql = "UPDATE transaction SET tran_status_code = ? ";

		System.out.println("2. 쿼리객체 생성");

		if (purchase.getTranCode().equals("2")) {

			String sql = "UPDATE transaction SET tran_status_code = ? WHERE prod_no = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, purchase.getTranCode());
			pstmt.setInt(2, purchase.getPurchaseProd().getProdNo());

			System.out.println("3. 쿼리 전송");
			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("4. 쿼리 전송 성공");
			}

			pstmt.close();
			con.close();

		} else if (purchase.getTranCode().equals("3")) {

			String sql2 = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";

			PreparedStatement pstmt = con.prepareStatement(sql2);

			pstmt.setString(1, purchase.getTranCode());
			pstmt.setInt(2, purchase.getTranNo());

			System.out.println("3. 쿼리 전송");
			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("4. 쿼리 전송 성공");
			}

			pstmt.close();
			con.close();
		}

	}

}
