package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListDAO;

/**
 * Servlet implementation class RewardServlet
 */
@WebServlet("/RewardServlet")
public class RewardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションからIDを取得
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("id");
		//今日の日付をSQLのDATE型でもっておく
		java.sql.Date today = makeSqlDate(0);

		//今日の日付とidに該当するリストがあるときはの終了チェックをTRUEにする
		ListDAO lDao = new ListDAO();
		List<model.List> check = lDao.listCheck(new model.List(0,today,id,false));

		if(check.size() == 1 && check.get(0).getCheck_tf() == false) {

		}else {
			//そうじゃないときはTopに返す
			response.sendRedirect("/osilis/TopServlet");
		}
		//達成チェックがTRUEのリストデータの最終達成日時をUpdateする

		//DBメソッドでユーザーのポイントと称号を取得する
		//DBメソッドでリストを取得する
		//ユーザー型のオブジェクトを作ってスコープに入れる
		//リスト型のオブジェクトをつくってスコープに入れる


		// 報奨ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reward.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
