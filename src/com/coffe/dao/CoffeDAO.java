package com.coffe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.coffe.vo.CoffeOrderVO;
import com.coffe.vo.CoffeSaleVO;
import com.mysql.cj.xdevapi.PreparableStatement;

public class CoffeDAO {
	
	public  List<HashMap<String, Object>> getCoffeList() {
		List<HashMap<String , Object>> list = new ArrayList<HashMap<String , Object>>();
//		List<CoffeSaleVO> list = new ArrayList<CoffeSaleVO>(); 
		try {
			String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String id = "root";
			String pass = "1234";
			String driver = "com.mysql.jdbc.Driver";
			Connection con = DriverManager.getConnection(url, id, pass);

			Statement st = con.createStatement();
			ResultSet rs = null;
			String sql = "select tcm.COFFE_ID, tcm.COFFE_NM, tcn.PRICE " 
					+ "  from TB_COFFE_001MT tcm,"
					+ "       TB_COFFE_002NT tcn" 
					+ " where tcm.USEYN ='Y'" 
					+ "   AND tcn.PRICE_DATE ='202403'"
					+ "   AND tcm.COFFE_ID = tcn.COFFE_ID";
			
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int coffeId = rs.getInt("COFFE_ID");
				String coffeNm = rs.getString("COFFE_NM");
				int price = rs.getInt("PRICE");
				HashMap<String , Object> map = new HashMap<>();
				map.put("coffeId",coffeId);
				map.put("coffeNm",coffeNm);
				map.put("price",price);

				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	
	}
	public List<HashMap<String , Object>> getDailySales() {

		List<HashMap<String , Object>> list = new ArrayList<HashMap<String , Object>>();

		try {
			String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String id = "root";
			String pass = "1234";
			String driver = "com.mysql.jdbc.Driver";
			Connection con = DriverManager.getConnection(url, id, pass);

			Statement st = con.createStatement();
			ResultSet rs = null;

			String sql = "SELECT DATE_FORMAT(tsn.SALE_DATE, '%Y-%m-%d')as SALE_DATE ,tcm.COFFE_ID, tcm.COFFE_NM, "
					+ "        tcn.PRICE, SUM(tsn.SALE_CNT) AS SALE_CNT " 
					+ "   FROM TB_COFFE_001MT tcm, "
					+ "        TB_COFFE_002NT tcn, " 
					+ "        TB_SALES_001NT tsn " 
					+ "  WHERE tcm.USEYN ='Y' "
					+ "    AND tcn.PRICE_DATE ='202403' " 
					+ "    AND tcm.COFFE_ID = tcn.COFFE_ID "
					+ "    AND tcm.COFFE_ID = tsn.COFFE_ID "
					+ "    AND DATE_FORMAT(tsn.SALE_DATE, '%Y-%m-%d') = DATE_FORMAT(now(), '%Y-%m-%d') "
					+ "  GROUP BY DATE_FORMAT(tsn.SALE_DATE, '%Y-%m-%d'),tcm.COFFE_ID, tcm.COFFE_NM, tcn.PRICE;";
			
			rs = st.executeQuery(sql);
			//select 문에 사용
			while (rs.next()) {
				Date saleDate = rs.getDate("SALE_DATE");
				int coffeId = rs.getInt("COFFE_ID");
				String coffeNm = rs.getString("COFFE_NM");
				int price = rs.getInt("PRICE");
				int saleCnt = rs.getInt("SALE_CNT");
				HashMap<String , Object> map = new HashMap<>();
				map.put("saleDate",saleDate);
				map.put("coffeId",coffeId);
				map.put("coffeNm",coffeNm);
				map.put("price",price);
				map.put("saleCnt",saleCnt);

				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	//주문서 입력
	public void setOrderMenu(List<HashMap<String , Object>>orderlist) {
		// TODO Auto-generated method stub
		
		try {
			String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String id = "root";
			String pass = "1234";
			String driver = "com.mysql.jdbc.Driver";
			Connection con = DriverManager.getConnection(url, id, pass);
			PreparedStatement psmt = null;
			String sql = " insert  into  tb_sales_001nt (COFFE_ID, SALE_CNT) "
					+ "   values(?,?)";
			//DB에서 돌려보고 넣기
			
			for(HashMap<String , Object> map :orderlist) {
				int coffeId = (int) map.get("coffeId");
				int coffeCnt = (int) map.get("coffeCnt");
				psmt = con.prepareStatement(sql);

				psmt.setInt(1, coffeId);
				psmt.setInt(2, coffeCnt);
				
				psmt.executeUpdate();//insert 나 Update 할때 사용
			}

				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		public void setOrderMenuAdd(HashMap<String , Object>orderlistadd) {
			// TODO Auto-generated method stub
			
			try {
				String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String id = "root";
				String pass = "1234";
				String driver = "com.mysql.jdbc.Driver";
				Connection con = DriverManager.getConnection(url, id, pass);
				PreparedStatement psmt = null;
				String sql = " insert  into  tb_coffe_001mt (COFFE_NM) "
						+ "	 values(?) ";
				//DB에서 돌려보고 넣기
				
					String COFFENM = (String) orderlistadd.get("COFFE_NM");
			
					psmt = con.prepareStatement(sql);

					psmt.setString (1, COFFENM);
					
					psmt.executeUpdate();//insert 나 Update 할때 사용
				

					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			public void setOrderpriceAdd(HashMap<String,Object> orderlistadd) {
				// TODO Auto-generated method stub
				
				try {
					String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
					String id = "root";
					String pass = "1234";
					String driver = "com.mysql.jdbc.Driver";
					Connection con = DriverManager.getConnection(url, id, pass);
					PreparedStatement psmt = null;
					String sql = " insert  into tb_coffe_002nt (COFFE_ID , PRICE, PRICE_DATE) "
							+ "	 VALUES (SELECT max(COFFE_ID)) AS COFFE_ID  "
							+ "	 	FROM tb_coffe_001mt "
							+ "	 	LIMIT 1), (?),  DATE_FORMAT(tb_coffe_002nt.PRICE_DATE , '%Y%m') ";
					//DB에서 돌려보고 넣기
					
					
						int PRICE =  (int) orderlistadd.get("PRICE");
				
						psmt = con.prepareStatement(sql);

						psmt.setInt (1, PRICE);
						
						psmt.executeUpdate();//insert 나 Update 할때 사용
					

						
				} catch (Exception e) {
					e.printStackTrace();
				}
	
	
		}
}
