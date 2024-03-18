package com.user;

import com.user.dao.UserDAO;
import com.user.vo.UserVO;

public class Program {

	public static void main(String[] args) {

		UserVO[] ua = new UserVO[5];// UserVO형 5개짜리 객체 생성
		UserDAO ud = new UserDAO();// DB접속과 실행을 위한 DAO객체 생성
		ua = ud.getUserInfo(5);// DAO의 getUserInfo에 배열값 5개를 보냄

		for (UserVO uv : ua) {
			int userNo = uv.getUSERNO();
			String userId = uv.getUSERID();
			String userNm = uv.getUSERNM();
			String userPw = uv.getUSERPW();
			String userRole = uv.getUSERROLE();

			System.out.printf("| %d | %s| %s| %s| %s|￦d", userNo, userId, userNm, userPw, userRole);
			System.out.println();
		}
	}

}
