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
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		//今はdojoで固定
		String id = "dojo";

		if(request.getParameter("data2") == null){

			//履歴ページにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pastList.jsp");
			dispatcher.forward(request, response);
		}else {
			// 送信されたデータの取得
			//JavaSriptのdata2を取得
			String data2 = request.getParameter("data2");
			String date = data2.replace("/","-");
			java.sql.Date clickDate = java.sql.Date.valueOf(date);

			//クリックされた日付を受け取り、list_dataとeventsの結合テーブルから取得してデータを送る
			//IDともらった日付を検索して、Listの番号を取得する。
			ListDAO lDao = new ListDAO();
			List<model.List> pastListList = lDao.listCheck(new model.List(0,clickDate,id,false));
			model.List pastList = pastListList.get(0);


			int pastList_num = pastList.getNumber();
			//list_dataからList番号のデータを取り出す
			List<Events> pastListData = lDao.selectList(id,pastList_num);


			for(Events a:pastListData) {
			System.out.println(a.getNumber());
			System.out.println(a.getEvent());
			System.out.println(a.getType());
			System.out.println(a.getLevel());
			System.out.println(a.getAvailable());
			System.out.println(a.getUser_id());
			System.out.println(a.getCheck_tf());
			}
			//インスタンス化


			ObjectMapper mapper = new ObjectMapper();
			try {
	            //JavaオブジェクトからJSONに変換
	            String pastListJson = mapper.writeValueAsString(pastList);

	            //JSONの出力
	            response.getWriter().write(pastListJson);

	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		}



		//更新ボタンが押されたときはリストの更新をする。


	}

}
