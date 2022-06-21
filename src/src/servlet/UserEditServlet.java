package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.UsersDAO;
import model.Result;
import model.User;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
@MultipartConfig(location = "C:\\dojo6\\src\\WebContent\\imgs") //アップロードファイルの一時的な保存先
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//現在のユーザー情報を取得
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("id") == null) {
					response.sendRedirect("/osilis/LoginServlet");
					return;
				}

		// イベント編集画面にフォワードする
		//すでに保持しているデータ表示のためのセレクト
		UsersDAO uDao = new UsersDAO();
		String id = (String)session.getAttribute("id");
		System.out.println("idは"+id);


		User user = uDao.userSelect(id);


		request.setAttribute("user", user);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userEdit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		//編集されたところを上書きして保存
		//アイコン,ユーザ名設定

		UsersDAO uDao = new UsersDAO();

		if (request.getParameter("User_Regist") != null) {

			String user_name = request.getParameter("UserName");
			String id = (String)session.getAttribute("id");

			//指定したユーザーのuser情報を一度取得する。
			User user = uDao.userSelect(id);

			//新しい画像が選択されているかチェックする。iconのファイル名が空文字かそうでないかで判断できる
			if(request.getPart("icon") != null) {
				Part part = request.getPart("icon");
				String icon =this.getFileName(part);
				UsersDAO dao  = new UsersDAO();
				User u = dao.userSelect(id);

				System.out.println(u.getIcon()+"aaaaaaaaa");
		try {
					part.write(icon);
					user.setIcon(icon);
				}
		//catch(FileNotFoundException e) {
		//}
			catch(IOException e) {
				user.setIcon(u.getIcon());
			}


				//System.out.println(icon);

				//ユーザネーム更新
				user.setUser_name(user_name);

				//サーバにアップロードした画像ファイルを保存する。
				if (uDao.userUpdate(user)) {	// 更新成功
					request.setAttribute("result",
					new Result("更新成功！"));
				}
				else {												// 更新失敗
					request.setAttribute("result",
					new Result("更新失敗！"));
				}
			}

			else {
				//ユーザネーム更新
				user.setUser_name(user_name);
				if (uDao.userUpdate(user)) {	// 更新成功
					request.setAttribute("result",
					new Result("更新成功！"));
				}
				else {												// 更新失敗
					request.setAttribute("result",
					new Result("更新失敗！"));
				}
			}

			//フォワード
		user = uDao.userSelect(id);
		request.setAttribute("user",user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userEdit.jsp");
		dispatcher.forward(request, response);
		}

		else {
			//パスワード変更
			String password = request.getParameter("Password");
			String change_password = request.getParameter("Change_Password");
			String check_password = request.getParameter("Check_Password");

			if (session.getAttribute("pass").equals(password) && change_password.equals(check_password)) {
				if(uDao.passUpdate(new User((String)session.getAttribute("id"),change_password))) { // 更新成功
					request.setAttribute("result",
					new Result("更新成功！"));
					}
				}
				else {												// 更新失敗
					request.setAttribute("result",
					new Result("更新失敗！"));
				}
			//フォワード
			String id = (String)session.getAttribute("id");


			//指定したユーザーのuser情報を一度取得する。

			User user = uDao.userSelect(id);

			user = uDao.userSelect(id);
			request.setAttribute("user",user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userEdit.jsp");
			dispatcher.forward(request, response);
			}


		}



	//ファイルの名前を取得してくる
		private String getFileName(Part part) {
	        String name = null;
	        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
	            if (dispotion.trim().startsWith("filename")) {
	                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
	                name = name.substring(name.lastIndexOf("\\") + 1);
	                break;
	            }
	        }
			return name;
		}
}
