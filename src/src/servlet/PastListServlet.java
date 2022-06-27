package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ListDAO;
import dao.UsersDAO;
import model.Events;
import model.ListData;
import model.Result;
import model.User;

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
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("id") == null) {
				response.sendRedirect("/osilis/LoginServlet");
					return;
				}

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
		///セッションからIDを取得
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("id");


		if(request.getParameter("data2") == null){
			//カレンダー以外を押された時＝完了ボタンを押したときのメソッド
			int listNum = Integer.parseInt(request.getParameter("listNum"));
			String[] array = request.getParameterValues("check_tf");

			//もらったlist_numのcheck_tfをtrueにする
			//System.out.println(listNum);
			/*
			for(String a:array) {
			System.out.println(a);
			}
			*/
			ListDAO lDao = new ListDAO();
			//Listのcheck_tfをtrueに変える
			lDao.tfUpdate(new model.List(listNum,null,id,true));

			//もらったarrayを数字に戻す
			//チェックのついてるデータのvalue(list_dataNum)を取得する
			List<Integer>list_dataNumList = new ArrayList<>();
				int l = 0;
				if(array != null) {
					for(String a :array){
					  l = Integer.parseInt(a);
						list_dataNumList.add(l);
					}
				}
				//リストをつくった日を拾う
				model.List list = lDao.selectList(listNum);
				Date listDate = list.getDate();

			try {
				//list_dataNumのcheck_tfをtrueにかえる。
				for(int listDataNum: list_dataNumList) {
					//送られてきたlistDataの番号についてtrueにする
					lDao.listDataCheck_tfUpdate(listDataNum, true);

					//list_dataの最終達成日を取得する
					ListData listData = lDao.selectListData(listDataNum);
					Date listDataDate = listData.getCheck_date();
					//list_dataの最終達成日を拾う
					//達成チェックがtrueになっているlist_dataのcheck_dateを引数の日にちにする。
					if(listDataDate.before(listDate)) {
						lDao.checkDateUpdate(listNum,(java.sql.Date)listDate);
					}
				}


			}catch(NullPointerException e) {
				for(int listDataNum: list_dataNumList) {
					//送られてきたlistDataの番号についてtrueにする
					lDao.listDataCheck_tfUpdate(listDataNum, true);
					//リスト作成日をlist_dataの最終達成日にする
					lDao.checkDateUpdate(listNum,(java.sql.Date)listDate);
				}

			}finally {


				//ポイント計算
				//半分
				int past_point = lDao.Total_P(listNum)/2;

				UsersDAO uDao = new UsersDAO();
				User user = uDao.userSelect(id);
				//userに追加。
				int total_point = past_point + user.getPoint();
				uDao.pointUpdate(total_point,id);

				user.setPoint(total_point);
				request.setAttribute("result",
						new Result(past_point +"ポイント追加しました！現在"+total_point+"ポイントです。"));


				//履歴ページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pastList.jsp");
				dispatcher.forward(request, response);
			}
		}else {
			try {
				//日付をクリックされたときのこと
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

				boolean listCheck_tf = pastListList.get(0).getCheck_tf();
				int pastList_num = pastList.getNumber();
				//list_dataからList番号のデータを取り出す
				List<Events> pastListData = lDao.selectList(id,pastList_num);

				//pastListDataにlistの番号とpastListの最終達成チェックの情報を入れる。
				for (Events p : pastListData) {
					p.setListNum(pastList_num);
					p.setListCheck_tf(listCheck_tf);
				}

				ObjectMapper mapper = new ObjectMapper();
	            //JavaオブジェクトからJSONに変換

	            String pastListDataJson = mapper.writeValueAsString(pastListData);
	            System.out.println(pastListDataJson);
	           // System.out.println(pastListJson);

	            //JSONの出力
	            response.getWriter().write(pastListDataJson);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();

	        } catch(IndexOutOfBoundsException e) {
	        	Events error = new Events();
	        	List<Events> errorList = new ArrayList<>();
	        	errorList.add(error);
	        	errorList.add(error);

	        	ObjectMapper mapper = new ObjectMapper();
	            //JavaオブジェクトからJSONに変換

	            String errorJson = mapper.writeValueAsString(errorList);
	            System.out.println(errorJson);
	           // System.out.println(pastListJson);

	            //JSONの出力
	            response.getWriter().write(errorJson);
	        }
		}



		//更新ボタンが押されたときはリストの更新をする。


	}

}
