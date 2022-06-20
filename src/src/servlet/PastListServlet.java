package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ListDAO;
import model.Events;

/**
 * Servlet implementation class PastListServlet
 */
@WebServlet("/PastListServlet")
public class PastListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//履歴ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pastList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//今はdojoで固定
		String id = "dojo";

		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");

		// 送信されたデータの取得
		//JavaSriptのdata2を取得
		String data2 = request.getParameter("data2");
		String date = data2.replace("/","-");
		java.sql.Date clickDate = java.sql.Date.valueOf(date);

		//クリックされた日付を受け取り、list_dataとeventsの結合テーブルから取得してデータを送る
		//IDともらった日付を検索して、Listの番号を取得する。
		ListDAO lDao = new ListDAO();
		List<model.List> clickList = lDao.listCheck(new model.List(0,clickDate,id,false));
		int	 list_num = clickList.get(0).getNumber();
		//list_dataからList番号のデータを取り出す
		List<Events> pastList = lDao.selectList(id,list_num);

		for(Events a:pastList) {
		System.out.println(a.getEvent());
		}
		//インスタンス化


		ObjectMapper mapper = new ObjectMapper();
		try {
            //JavaオブジェクトからJSONに変換
            String testJson = mapper.writeValueAsString(pastList);
            //JSONの出力
            response.getWriter().write(testJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



		//更新ボタンが押されたときはリストの更新をする。


	}

}
