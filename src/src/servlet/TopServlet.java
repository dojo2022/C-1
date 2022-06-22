package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListDAO;
/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//idと日付を取得してif文で分岐させる

		//今日の日付
		java.sql.Date today = makeSqlDate(0);
		//セッションID
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("id");

		//リストの数を取得する
		ListDAO lDao = new ListDAO();
		List<model.List> listCheck = lDao.listCheck(new model.List(0,today,id,false));

		//もしデータベースにリストがなかったら
		if(listCheck.size()==0) {
			//CreateListServletにリダイレクト
			response.sendRedirect("/osilis/CreateListServlet");
		//もしデータベースにリストがあるかつ、checkがfalseなら

		}else if(listCheck.size() == 1 && listCheck.get(0).getCheck_tf() == false ) {
			//ListServlet
			response.sendRedirect("/osilis/ListServlet");
		//もしデータベースにリストがあるかつ、chekcがtrueなら
		}else if(listCheck.size() == 1 && listCheck.get(0).getCheck_tf() == true) {
			//RewardServlet
			response.sendRedirect("/osilis/RewardServlet");
		}else {
			response.sendRedirect("/osilis/LoginServlet");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	//数字を引数に入れて、その分今日からマイナスしてSQL型の日付を提示するメソッド
	//引数が0なら今日！
	public static java.sql.Date makeSqlDate(int x){
		//最新日時取得
		Date date = new Date();
		//計算したいからCalendar型に入れる
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//引数分マイナスする
		c.add(Calendar.DAY_OF_MONTH, -x);
		date = c.getTime();
		//年月日に変換する
		String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		//SQLのDATE型に変換する
		java.sql.Date sqlDate = java.sql.Date.valueOf(formatDate);
		return sqlDate;
	}

}


