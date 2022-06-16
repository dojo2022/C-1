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
import model.Events;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DBメソッドでListテーブルから今日の日付とユーザーIDが一致するリスト番号を取得する
		//今日の日付をSQLのDATE型でもっておく
		java.sql.Date today = makeSqlDate(0);
		//IDをセッションスコープから拾ってくる
		String id ="dojo";
		model.List check = new model.List(0,today,id,false);
		ListDAO lDao = new ListDAO();
		List<model.List> checkList = lDao.listCheck(check);
		//リストデータテーブルから、リスト番号が一致するイベント番号を取得する
		int list_num = checkList.get(0).getNumber();

		//イベント番号が一致するイベントのデータを取得
		List<Events> eventsList = lDao.selectList(id,list_num);


		//リクエストスコープに入れる

		//list.jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);

		//画面から送信されたリストとチェックの有無を取得する
		//どの項目が達成したのか？チェックの有無を画面から送る必要があります。
		//チェックが外れたときは、リストデータの達成チェックを外して
		//beans格納指定

		//daoのinsert、update処理に依頼する。


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
