package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//日付を取得してはじきたい項目をListでうけとる

		//Listに入った数字をはじいた全体の数を取得する

		//全体の数と土日か平日かを渡してランダムに生成する。平日か週末かによって配分を変える。


		//リストサーブレットにリダイレクト
		response.sendRedirect("/osilis/CreateListServlet");
	}

}
