package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.Result;
import model.User;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

	//送られてきたIDとPASSWORDを取得する。
		String id = request.getParameter("ID");
		String pass = request.getParameter("PW");
		
	//変数の中身確認
			System.out.println(id);
			System.out.println(pass);
	//ログイン処理を行う
		UsersDAO uDao = new UsersDAO();
		if (uDao.isLoginOK(new User(id, pass))) {	// ログイン成功

	//ログイン成功時、セッションスコープにIDとPASSWORDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("pass", pass);

	//TOPServletにリダイレクトする
			response.sendRedirect("/osilis/TopServlet");

		}

		else {										//ログイン失敗
			//ログイン失敗時、リクエストスコープに、エラーメッセージ、戻り先を格納する
			request.setAttribute("result",
					new Result("ログイン失敗！"));
			//Login.jspにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		}

	}

}
