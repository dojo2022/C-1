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

import dao.ListDAO;
/**
 * Servlet implementation class CreateListServlet
 */
@WebServlet("/CreateListServlet")
public class CreateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リスト製作画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//平日か週末かを取得してリストをランダム作成する
		String id="dojo";
		java.sql.Date sqlDate = makeSqlDate(3);
		//日付を取得してはじきたい項目をList型でうけとる←ID取得し忘れた。(6/15)
		ListDAO lDao = new ListDAO();
		List<String> houseList = lDao.notIn(id,1,sqlDate);
		List<String> workList = lDao.notIn(id,2, sqlDate);
		List<String> indoorList = lDao.notIn(id,3, sqlDate);
		List<String> outdoorList = lDao.notIn(id,4,sqlDate);
		//List<String> list = lDao.notIn(type,sqldate);
		//Listに入った数字をはじいた全体の数を取得する
		int houseCount = lDao.count(id,1, houseList);
		int workCount = lDao.count(id,2,workList);
		int indoorCount = lDao.count(id,3, indoorList);
		int outdoorCount = lDao.count(id,4, outdoorList);
		//全体の数と土日か平日かを渡してランダムに生成する。平日か週末かによって配分を変える。
		/*
		if("平日") {

		}elseif("土日"){

		}
		*/
		//listテーブルにいれる。セッションスコープからidを取得する。今日の日時を取得する。

		//リストテーブルから今日の日付に一致するリスト番号と、
		//上で作ったイベントデータの番号を取得して、list_dataテーブルに入れる。

		//リストサーブレットにリダイレクト
		response.sendRedirect("/osilis/ListServlet");
	}
	//数字を引数に入れて、その分今日からマイナスしてSQL型の日付を提示するメソッド
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
