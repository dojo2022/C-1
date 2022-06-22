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
import model.UserFavoriteImg;
import model.UserFavoriteVoice;

/**
 * Servlet implementation class OsiServlet
 */
@WebServlet("/OsiServlet")
@MultipartConfig(location = "C:\\dojo6\\src\\WebContent\\imgs") //アップロードファイルの一時的な保存先
public class OsiServlet extends HttpServlet {
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

		//現在の推し画像と推し音声のデータを取得
		UsersDAO uDao = new UsersDAO();
		String id = (String)session.getAttribute("id");
		//System.out.println("idは"+id);

		//現在の推し画像取得
		UserFavoriteImg img = uDao.imgSelect(id);

		//現在の推しボイス取得
		UserFavoriteVoice voice = uDao.voiceSelect(id);

		//リクエストスコープに推し画像,ボイスのデータを格納
		request.setAttribute("img", img);
		request.setAttribute("voice", voice);


		// 推し編集ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/osi.jsp");
				dispatcher.forward(request, response);


			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String id = (String)session.getAttribute("id");
		UsersDAO uDao = new UsersDAO();

		//現在の情報取得
		//画像登録
		UserFavoriteImg img = uDao.imgSelect(id);

		//画像変更
		if (request.getParameter("Image_Regist") != null) {

			Part part = request.getPart("Good_Image");
			if(!this.getFileName(part).equals("")) {

				String Good_Image = this.getFileName(part);
				part.write(Good_Image);
				img.setFavorite_good_img(Good_Image);
			}

			Part part_2 = request.getPart("Bad_Image");
			if(!this.getFileName(part_2).equals("")) {

				String Bad_Image = this.getFileName(part_2);
				part_2.write(Bad_Image);
				img.setFavorite_bad_img(Bad_Image);
			}

			Part part_3 = request.getPart("Other_Image");
			if(!this.getFileName(part_3).equals("")) {

				String Other_Image = this.getFileName(part_3);
				part_3.write(Other_Image);
				img.setFavorite_other_img(Other_Image);
			}


			if(uDao.imgUpdate(img)) {
				request.setAttribute("result", new Result("更新成功！"));
			}
			else {
				request.setAttribute("result", new Result("更新失敗！"));
			}

			//フォワード
			img = uDao.imgSelect(id);
			request.setAttribute("img", img);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/osi.jsp");
			dispatcher.forward(request, response);

		}


		//音声変更
		else if (request.getParameter("Voice_Regist") != null){
			UserFavoriteVoice voice = uDao.voiceSelect(id);

			//変数にjspで入力したファイルを入れる
			Part part = request.getPart("Good_Voice");
			if(!this.getFileName(part).equals("")) {

				String Good_Voice = this.getFileName(part);
				part.write(Good_Voice);
				voice.setFavorite_good_voice(Good_Voice);
			}

			Part part_2 = request.getPart("Bad_Voice");
			if(!this.getFileName(part_2).equals("")) {

				String Bad_Voice = this.getFileName(part_2);
				part_2.write(Bad_Voice);
				voice.setFavorite_bad_voice(Bad_Voice);
			}

			Part part_3 = request.getPart("Other_Voice");
			if(!this.getFileName(part_3).equals("")) {

				String Other_Voice = this.getFileName(part_3);
				part_3.write(Other_Voice);
				voice.setFavorite_other_voice(Other_Voice);
			}



			if(uDao.voiceUpdate(voice)) {
				request.setAttribute("result", new Result("更新成功！"));
			}
			else {
				request.setAttribute("result", new Result("更新失敗！"));
			}

			//フォワード
			voice = uDao.voiceSelect(id);
			request.setAttribute("voice", voice);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/osi.jsp");
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
