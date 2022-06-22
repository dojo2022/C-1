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
import dao.UsersDAO;
import model.Events;
import model.User;

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



		//id = "dojo";
		//今日の日付をSQLのDATE型でもっておく
		java.sql.Date today = makeSqlDate(0);

		//IDと日付からそのユーザがその日につくったリストの情報をもっておく
		ListDAO lDao = new ListDAO();
		List<model.List> check = lDao.listCheck(new model.List(0,today,id,false));


		//リストの数が1かつそのリストの達成チェックがfalseの時。
		if(check.size() == 1 && check.get(0).getCheck_tf() == false) {

		//まずはリスト関連の動作
			//そのリストの番号を取得
			int list_num = check.get(0).getNumber();
			System.out.println(list_num);
			//そのリスト番号に該当する達成チェックをtrueにする
			lDao.tfUpdate(new model.List(list_num,today,id,true));

			//list_numberと今日の日付を渡して、達成チェックがTRUEのlist_dataの最終達成日時を今日の日付けにUpdateする
			lDao.checkDateUpdate(list_num, today);


		//次にuserテーブルのポイント関連
			//idを渡して、ユーザーのポイントと称号を取得する(UserDAOのSelect)
			UsersDAO uDao = new UsersDAO();
			User user = uDao.userSelect(id);




			//ポイント計算　userSelectで取得したユーザの元のポイントに、今日クリアしたリストの獲得ポイントを足す。
			//userのポイントを変数にいれる。…➀
			int total_point = user.getPoint();


			//今日獲得したポイントを計算する。
			//idと日付を渡して、その日にクリアしたイベントの合計得点を返す。…➁;
			int today_point = lDao.Total_P(today,id);


			//➀と➁を足して、テーブルをアップデートする。
			int today_total = total_point + today_point;
			user.setPoint(today_total);
			uDao.pointUpdate(today_total, id);



			//今日できたリストを取得する(List<Events>型)
			List<Events> clearList = lDao.selectList(id, list_num, true);
			int clearCount = clearList.size();


			//userに今日の獲得ポイントとクリアしたリストの数を格納する
			user.setTodayPoint(today_point);
			user.setClearCount(clearCount);

			//User型のオブジェクトを作ってスコープに入れる
			request.setAttribute("user", user);
			//List<Events>のオブジェクトをつくってスコープに入れる
			request.setAttribute("clearList", clearList);

			System.out.println(user.getUser_name());
			System.out.println(user.getTodayPoint());

			// 報奨ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reward.jsp");
			dispatcher.forward(request, response);


		}else {
			//そうじゃないときはただjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reward.jsp");
			dispatcher.forward(request, response);
		}

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
