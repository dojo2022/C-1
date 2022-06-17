package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		//スコープに入れる

		//履歴ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pastList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = "dojo";

		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");

		// 送信されたデータの取得

		String click = request.getParameter("click");
		String Aclick = click.replace("/","-");
		System.out.println(Aclick);

		java.sql.Date clickDate = java.sql.Date.valueOf(Aclick);

		//インスタンス化
		model.List list = new model.List(0,clickDate,id,false);

		System.out.println(list.getDate());

		ObjectMapper mapper = new ObjectMapper();
		try {
            //JavaオブジェクトからJSONに変換
            String testJson = mapper.writeValueAsString(list);
            //JSONの出力
            response.getWriter().write(testJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//		//文字コードの設定（めんどいのでコピペでOK）
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
//
//		//JSPに返却する値を作成する。値はoutの中に格納する
//		PrintWriter out = response.getWriter();
//		//outの中に持ってきたデータを連結したものを入れる
//		//勝手にJSPに渡り、dataという名前で使用することができる
//		out.print(data1+","+data2+","+data3);
//
//        return;



		//クリックされた日付を受け取り、list_dataとeventsの結合テーブルから取得してデータを送る


		//更新ボタンが押されたときはリストの更新をする。


	}

}
