package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Events;

public class ListDAO {
	//ランダム生成用のメソッド群
	//➀日付とtypeとidを受け取り、ランダムに入れたくない要素のnumberをListにいれて返す
	public List<String> notIn(String id,int type,Date date){
		Connection conn = null;
		List<String> noList = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql ="SELECT No FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE user_id= ? and type = ? and (check_date > ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,id);
			pStmt.setInt(2,type);
			pStmt.setDate(3,date);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				String no =rs.getString("No");
				noList.add(no);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			noList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			noList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					noList = null;
				}
			}
		}

		// 結果を返す
		return noList;
	}



	//➁listに入った数字をはじいて、ランダムしたい項目の項目数を戻す
	public int count(String id,int type,List<String> list) {
		Connection conn = null;
		int x = 0;
		String ids="";
		try {

			int size = list.size();
			if(size>0) {
				for(int i=0; i<size-1;i++) {
					ids = ids + list.get(i)+",";
				}
				ids = ids + list.get(size-1);
			}else {
				ids = "";
			}



			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");
			// SQL文を準備する
			String sql = "SELECT count(*)FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE No not in ("+ids+")and user_id = ? and type =?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,id);
			pStmt.setInt(2,type);

			// SQL文を実行し、iに代入する
			ResultSet rs = pStmt.executeQuery();
		    //1行目にカーソルを合わせる
			rs.next();
			x = rs.getInt(1);

		}
		catch (SQLException e) {
			e.printStackTrace();

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return x;
	}
	//ランダムにリストを作成する
	//引数リスト　list:はじきたいNo　type:家事仕事とか　x:ランダムの最大数　q:何個抽出するのか
	public List<Events> random (String id,int type,List<String>list,int x,int q){
		List<Events> eventList = new ArrayList<>();
		Connection conn = null;
		String ids ="";
		try{
			do {
				int size = list.size();
				if(size>0) {
					for(int i=0; i<size-1;i++) {
						ids += list.get(i)+",";
					}
					ids += list.get(size-1);
				}else {
					ids = "";
				}
				//ランダムで数字を一つ作る
				int r = (int)Math.ceil(Math.random() * x);

				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE No not in ("+ids+") and user_id = ? and type = ? limit 1 offset ? ";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1,id);
				pStmt.setInt(2,type);
				pStmt.setInt(3,r);

				// SQL文を実行し、iに代入する
				ResultSet rs = pStmt.executeQuery();
			    //1行目にカーソルを合わせる
				while (rs.next()) {
					//ListDataモデルに合わせて変更
					//ListDataのbeansを拡張する(6/14)
					//Servletでどういう

					Events event = new Events(
						rs.getInt("NUMBER"),
						rs.getString("EVENT"),
						rs.getInt("TYPE"),
						rs.getInt("LEVEL"),
						rs.getInt("AVAILABLE"),
						rs.getString("user_id")
					);


				//cardListを確認して、cardが一致しなかったら追加する。
				boolean result = true;
				for(Events e:eventList) {
					if(e.getNumber()==event.getNumber()) {
						result = false;
						break;
					}else{
						continue;
					}
				}

				if(result) {
				eventList.add(event);
				}


				}
			}while(eventList.size()<q);

		}
		catch (SQLException e) {
			e.printStackTrace();

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return eventList;
	}

	//リストを新しく追加するやつ
	public boolean listInsert(model.List list) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO LIST(date,id) VALUES(?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setDate(1,list.getDate());
			pStmt.setString(2,list.getId());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}



	//listから数字確保したり、同じ日付IDがあるかないか確かめたり用のメソッド
	public List<model.List> listCheck(model.List newList){
		Connection conn = null;
		model.List list = new model.List();
		List<model.List> todayList = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM LIST WHERE date = ? and id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setDate(1, newList.getDate());
			pStmt.setString(2, newList.getId());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				list.setNumber(rs.getInt("number"));
				list.setDate(rs.getDate("date"));
				list.setId(rs.getString("ID"));
				list.setCheck_tf(rs.getBoolean("check_tf"));

				todayList.add(list);
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
			todayList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			todayList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					todayList = null;
				}
			}
		}
		// 結果を返す
		return todayList;
	}

	//list_dataにいれる。
	public boolean list_dataInsert(int list_num, List<Events>list_data) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			for(Events data: list_data) {
				// SQL文を準備する
				String sql = "INSERT INTO list_data(list_num,event_num) VALUES(?,?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1,list_num);
				pStmt.setInt(2,data.getNumber());
				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}


	//ListServletとpastListServletでEventとそれぞれのチェックリストを取得する用のメソッド
	public List<Events> selectList (String id, int list_num){
		Connection conn = null;
		List<Events> eventList = new ArrayList<>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM events inner join list_data on events.number = list_data.event_num where user_id = ? and list_num =?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,id);
			pStmt.setInt(2,list_num);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Events event =new Events();
				event.setNumber(rs.getInt("number"));
				event.setEvent(rs.getString("Event"));
				event.setType(rs.getInt("TYPE"));
				event.setCheck_tf(rs.getBoolean("check_tf"));

				eventList.add(event);
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
			eventList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			eventList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					eventList = null;
				}
			}
		}
		// 結果を返す
		return eventList;
	}


	//ListServletでEventとそれぞれのチェックリストを取得する用のメソッド
		public List<Events> selectList (String id, int list_num ,boolean check_tf){
			Connection conn = null;
			List<Events> eventList = new ArrayList<>();
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備する
				String sql ="SELECT * FROM events inner join list_data on events.number = list_data.event_num where user_id = ? and list_num =? and check_tf = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1,id);
				pStmt.setInt(2,list_num);
				pStmt.setBoolean(3, check_tf);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					Events event =new Events();
					event.setNumber(rs.getInt("number"));
					event.setEvent(rs.getString("Event"));
					event.setType(rs.getInt("TYPE"));
					event.setCheck_tf(rs.getBoolean("check_tf"));

					eventList.add(event);
					}
			}
			catch (SQLException e) {
				e.printStackTrace();
				eventList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				eventList = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						eventList = null;
					}
				}
			}
			// 結果を返す
			return eventList;
		}


	//プリセット登録用のメソッド
	public boolean insertPreset(List<Events> list,String id) {
		boolean result = false;

		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			for(Events event:list) {

				// SQL文を準備する
				String sql = "INSERT INTO EVENTS(event,type,level,user_id) VALUES(?,?,?,?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1,event.getEvent());
				pStmt.setInt(2,event.getType());
				pStmt.setInt(3,event.getLevel());
				pStmt.setString(4,id);


				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


		return result;
	}
	//報奨画面でリストテーブルのcheck_tfを変更するメソッド
	public boolean tfUpdate(model.List list) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");



			// SQL文を準備する
			String sql = "UPDATE LIST SET (check_tf)=(?) WHERE number = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setBoolean(1,list.getCheck_tf());
			pStmt.setInt(2,list.getNumber());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}


		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}



		return result;
	}

	//ListServletでlist_dataのチェックボックスを操作したときのメソッド
	public boolean listDataCheck_tfUpdate(int number,boolean check_tf) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE list_data SET (check_tf)=(?) WHERE number = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setBoolean(1, check_tf);
			pStmt.setInt(2, number);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	//達成チェックがtrueになっているlist_dataのcheck_dateを引数の日にちにする。
	public boolean checkDateUpdate(int list_num, Date date) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");



			// SQL文を準備する
			String sql = "UPDATE LIST_DATA SET (check_date)=(?) WHERE list_num = ? and check_tf = true;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setDate(1, date);
			pStmt.setInt(2, list_num);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}


		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}


}


