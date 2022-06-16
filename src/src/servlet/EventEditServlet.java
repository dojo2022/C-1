package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventDAO;
import model.Result;
/**
 * Servlet implementation class EventEdit
 */
@WebServlet("/EventEditServlet")
public class EventEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 予定編集ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventEdit.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		//セッションスコープからIDを取得
		// リクエストパラメータを取得する
		//Intにしたらエラー出る
		request.setCharacterEncoding("UTF-8");
		int number = Integer.parseInt(request.getParameter("number"));
		String event = request.getParameter("event");
		int type = Integer.parseInt(request.getParameter("type"));
		int level = Integer.parseInt(request.getParameter("level"));
		int available = Integer.parseInt(request.getParameter("available"));
		String user_id = request.getParameter("user_id");

		//登録時、登録内容をeventsテーブルへ送る

		EventDAO bDao = new EventDAO();
		if (bDao.eventRegist(event,type,level,user_id)) {	// 登録成功
			request.setAttribute("result",
			new Result("登録成功！"));
		}
		else {												// 登録失敗
			request.setAttribute("result",
			new Result("登録失敗！"));
		}

		//編集時、有効・無効・非表示の値をeventsテーブルに送る

	}

}
