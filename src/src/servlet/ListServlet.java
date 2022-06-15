package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//リストデータテーブルから、リスト番号が一致するイベント番号を取得する
		//イベント番号が一致するイベントのデータを取得
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

}
