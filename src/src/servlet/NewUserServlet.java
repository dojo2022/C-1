package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.Result;

/**
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//新規登録ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//おくられてきたIDとPASSWORD、ユーザ名をUserテーブルに登録する
		String user = request.getParameter("User");
		String new_id = request.getParameter("New_ID");
		String new_password = request.getParameter("New_Password");

		UsersDAO uDao = new UsersDAO();
		if (uDao.userRegist(new_id,new_password,user)) {
			//favarite_imgテーブルとfavorite_voiceテーブルに送られてきたIDを登録する
			uDao.imgRegist(new_id);
			uDao.voiceRegist(new_id);

			//Login.jspにフォワードする
			request.setAttribute("result",
					new Result("登録成功！"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			//IDがほかのユーザーと同じだった場合
			//失敗時、リクエストスコープにエラーメッセージを格納
			request.setAttribute("result",
					new Result("登録失敗！"));

			//newUser.jspにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
		}
	}

}
