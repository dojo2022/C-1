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
import model.User;
import model.UserFavoriteImg;
import model.UserReward;
/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("id") == null) {
				response.sendRedirect("/osilis/LoginServlet");
					return;
				}

		//称号とポイントを取得してリクエストスコープに格納する

		//DAOのメソッドを使って、称号とポイントを取得して、User型JavaBeansに格納する
		String id= (String)session.getAttribute("id");
		UsersDAO uDao = new UsersDAO();

		User user = uDao.userSelect(id);

		//リクエストスコープ
		request.setAttribute("user",user);


		//画像取得
		UserFavoriteImg img = uDao.imgSelect(id);
		request.setAttribute("img", img);

		//更新した称号を取得する
		int point = user.getPoint();
		//pointを切り捨てる
		int reward_point= (int)Math.floor(point/100)*100;

		//今のポイントに対応するコード取得
		UserReward userReward = uDao.rewardSelect(reward_point);

		//updateメソッドでユーザのリワードコードを更新
		int code= userReward.getCode();
		user.setReward(code);
		uDao.rewardUpdate(user);

		UserReward reward_result = uDao.rankSelect(id);

		request.setAttribute("reward_result", reward_result);



		// マイページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
