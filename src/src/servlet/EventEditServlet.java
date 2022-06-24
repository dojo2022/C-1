package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventDAO;
import model.Events;
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
		///セッションからIDを取得
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("id");

		//そのユーザが持ってるevent情報を全部取得する
		EventDAO eDao = new EventDAO();
		List<Events> eventsList = eDao.eventSelect(id);

		for(Events e: eventsList) {
			System.out.println(e.getEvent());
			System.out.println(e.getAvailable());
		}

		//リクエストスコープにいれる
		request.setAttribute("eventsList", eventsList);

		// 予定編集ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventEdit.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///セッションからIDを取得
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("id");

		// リクエストパラメータを取得する
		//Intにしたらエラー出る
		request.setCharacterEncoding("UTF-8");


		//登録時、登録内容をeventsテーブルへ送る
		EventDAO bDao = new EventDAO();
		if (request.getParameter("Event_Regist") !=null) {
			String event = request.getParameter("Event");
			int type = Integer.parseInt(request.getParameter("Type"));
			int level = Integer.parseInt(request.getParameter("Level"));

			System.out.println("aaa:" + event);
			System.out.println("aaa:" + type);
			System.out.println("aaa:" + level);

			if (bDao.eventRegist(event,type,level,id)) {	// 登録成功
				request.setAttribute("result",
				new Result("登録成功！"));
			}
			else {												// 登録失敗
				request.setAttribute("result",
				new Result("登録失敗！"));
			}
		}

		//編集時、有効・無効・非表示の値をeventsテーブルに送る
		else {
			//表示しているイベント数分
			String[] numbers = request.getParameterValues("number");
			String[] event = request.getParameterValues("Event_Edit");
			String[] types = request.getParameterValues("Type_Edit");
			String[] levels = request.getParameterValues("Level_Edit");
			String[] availables = request.getParameterValues("Switch");


			for (int i = 0; i < event.length; i++) {
				int number = Integer.parseInt(numbers[i]);
				int type = Integer.parseInt(types[i]);    //最初のイベントの表示、表示データが取れる
				int level = Integer.parseInt(levels[i]);
				int available=Integer.parseInt(availables[i]);
				//１件ずつの更新処理を行う。
				if (bDao.eventEdit(new Events(number,event[i], type, level, available, id))) {	// 更新成功
					request.setAttribute("result",
					new Result("更新成功！"));
				}
				else {												// 更新失敗
					request.setAttribute("result",
					new Result("更新失敗！"));
				}
			}
		}
		doGet(request, response);
	}

}
