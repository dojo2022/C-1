package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import model.Events;
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
		request.setCharacterEncoding("UTF-8");
		//String wd = request.getParameter("week");
		//セッションスコープからIDを取得する(今はdojoで固定)

		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("id");

		System.out.println("げげげの"+id);
		//送信された曜日を取得
		String wd = request.getParameter("week");

		System.out.println("今日は"+wd);


		//今日の日付をSQLのDATE型でもっておく
		java.sql.Date today = makeSqlDate(0);
		ListDAO lDao = new ListDAO();

		List<model.List> check = lDao.listCheck(new model.List(0,today,id,false));
		if(check.size() == 0) {


			List<Events> house = new ArrayList<Events>();
			List<Events> work = new ArrayList<Events>();
			List<Events> indoor = new ArrayList<Events>();
			List<Events> outdoor = new ArrayList<Events>();

			java.sql.Date sqlDate = makeSqlDate(3);
			//IDと日付を取得してはじきたい項目をList型でうけとる

			List<String> houseList = lDao.notIn(id,1,sqlDate);
			List<String> workList = lDao.notIn(id,2, sqlDate);
			List<String> indoorList = lDao.notIn(id,3, sqlDate);
			List<String> outdoorList = lDao.notIn(id,4,sqlDate);

			//Listに入った数字をはじいた全体の数を取得する
			int houseCount = lDao.count(id,1, houseList);
			int workCount = lDao.count(id,2,workList);
			int indoorCount = lDao.count(id,3, indoorList);
			int outdoorCount = lDao.count(id,4, outdoorList);


			//全体の数と土日か平日かを渡してランダムに生成する。平日か週末かによって配分を変える。

			if(wd == "平日") {
				house = lDao.random(id,1,houseList,houseCount,2);
				work = lDao.random(id,2,workList,workCount,2);
				indoor = lDao.random(id,3,indoorList,indoorCount,1);
				outdoor = lDao.random(id,4,outdoorList,outdoorCount,1);

				for(Events h: work) {
					System.out.println(h.getNumber());
					System.out.println(h.getEvent());
					System.out.println(h.getType());
					System.out.println(h.getLevel());
					System.out.println(h.getAvailable());
					System.out.println(h.getUser_id());
				}
			}else if(wd == "休日"){
				house = lDao.random(id,1,houseList,houseCount,2);
				work = lDao.random(id,2,workList,workCount,0);
				indoor = lDao.random(id,3,indoorList,indoorCount,2);
				outdoor = lDao.random(id,4,outdoorList,outdoorCount,2);
			}

			//listテーブルに新しいリストを作る
			//if文で分岐させたい(6/16)
			lDao.listInsert(new model.List(0,today,id,false));

			//リストテーブルから今日の日付に一致するリスト番号と、
			//上で作ったイベントデータの番号を取得して、list_dataテーブルに入れる。
			List<model.List> todayList = lDao.listCheck(new model.List(0,today,id,false));
			int list_num = todayList.get(0).getNumber();

			lDao.list_dataInsert(list_num, house);
			lDao.list_dataInsert(list_num, work);
			lDao.list_dataInsert(list_num, indoor);
			lDao.list_dataInsert(list_num, outdoor);

			//リストサーブレットにリダイレクト
			response.sendRedirect("/osilis/ListServlet");

		}else {
			//今日もうリスト作られてますよ！！
			response.sendRedirect("/osilis/ListServlet");
		}

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
