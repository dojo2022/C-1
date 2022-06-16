package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListDAO;
import dao.UsersDAO;
import model.Events;
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

			List<Events> preset = new ArrayList<>();
			preset.add(new Events("洗濯する",1,2));
			preset.add(new Events("机掃除する",1,1));
			preset.add(new Events("トイレ掃除する",1,2));
			preset.add(new Events("ベッドメイキングする",1,1));
			preset.add(new Events("靴磨きする",1,2));
			preset.add(new Events("換気扇掃除する",1,3));
			preset.add(new Events("下駄箱掃除する",1,1));
			preset.add(new Events("ベランダ掃除する",1,1));
			preset.add(new Events("料理する",1,3));
			preset.add(new Events("席を譲る",2,1));
			preset.add(new Events("電車で立つ",2,2));
			preset.add(new Events("自分から挨拶する",2,1));
			preset.add(new Events("階段を使う",2,2));
			preset.add(new Events("ありがとうを5回言う",2,1));
			preset.add(new Events("ありがとうを5回言われる",2,3));
			preset.add(new Events("自分の考えを伝える",2,1));
			preset.add(new Events("自発的な行動をする",2,2));
			preset.add(new Events("頼られる",2,3));
			preset.add(new Events("30分前出勤する",2,3));
			preset.add(new Events("読書する",3,3));
			preset.add(new Events("腹筋を20回する",3,3));
			preset.add(new Events("実家に連絡とる",3,1));
			preset.add(new Events("腕立て伏せを10回する",3,3));
			preset.add(new Events("ドラマを見る",3,1));
			preset.add(new Events("ヨガをする",3,2));
			preset.add(new Events("デジタルデトックスする",3,3));
			preset.add(new Events("友人を家に呼ぶ",3,2));
			preset.add(new Events("自己啓発する",3,2));
			preset.add(new Events("推しを眺める",3,1));
			preset.add(new Events("散歩する",4,2));
			preset.add(new Events("ランニングする",4,3));
			preset.add(new Events("カラオケに行く",4,1));
			preset.add(new Events("水族館に行く",4,2));
			preset.add(new Events("買い物に行く",4,1));
			preset.add(new Events("サウナに行く",4,1));
			preset.add(new Events("公園に行く",4,1));
			preset.add(new Events("友人と出かける",4,1));
			preset.add(new Events("カフェに行く",4,2));
			preset.add(new Events("1万歩歩く",4,3));
			preset.add(new Events("5000歩歩く",4,2));

			//presetをinsertする
			ListDAO lDao = new ListDAO();
			lDao.insertPreset(preset,new_id);
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
