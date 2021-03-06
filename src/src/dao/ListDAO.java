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
import model.ListData;

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
			String sql ="SELECT * FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE user_id= ? and available = 0 and type = ? and (check_date > ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,id);
			pStmt.setInt(2,type);
			pStmt.setDate(3,date);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				String no =rs.getString("Number");
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
			String sql = "SELECT count(*)FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE Number not in ("+ids+")and user_id = ? and type =? and available = 0";

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
				int r = (int)Math.floor(Math.random() * (x+1));
				System.out.println(r);

				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE Number not in ("+ids+") and user_id = ? and type = ? and available = 0 limit 1 offset ? ";


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
						if(e.getNumber() == event.getNumber()) {
							result = false;
							break;
						}else{
							continue;
						}
					}
					System.out.println(result);
					if(result) {
					eventList.add(event);
					}


				}

				System.out.println(eventList.size());
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

	//listの番号から情報を一通り拾うメソッド
	public model.List selectList(int number){
		Connection conn = null;
		model.List list = new model.List();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM LIST WHERE NUMBER = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1,number);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				list.setNumber(rs.getInt("number"));
				list.setDate(rs.getDate("date"));
				list.setId(rs.getString("id"));
				list.setCheck_tf(rs.getBoolean("check_tf"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			list = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					list = null;
				}
			}
		}
		// 結果を返す
		return list;


	}

	//listの番号から情報を一通り拾うメソッド
		public ListData selectListData(int number){
			Connection conn = null;
			ListData listData = new ListData();
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備する
				String sql ="SELECT * FROM LIST_DATA WHERE NUMBER = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1,number);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					listData.setNumber(rs.getInt("number"));
					listData.setList_num(rs.getInt("List_num"));
					listData.setEvent_num(rs.getInt("Event_num"));
					listData.setCheck_tf(rs.getBoolean("check_tf"));
					listData.setCheck_date(rs.getDate("Check_date"));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				listData = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				listData = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						listData = null;
					}
				}
			}
			// 結果を返す
			return listData;


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
			String sql ="SELECT event,type,list_data.number,check_tf FROM events inner join list_data on events.number = list_data.event_num where user_id = ? and list_num =?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,id);
			pStmt.setInt(2,list_num);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Events event =new Events();

				event.setEvent(rs.getString("Event"));
				event.setType(rs.getInt("TYPE"));
				event.setList_dataNum(rs.getInt("Number"));
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
				String sql ="SELECT event,type,list_data.number,check_tf FROM events inner join list_data on events.number = list_data.event_num where user_id = ? and list_num =? and check_tf = ?;";
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

					event.setEvent(rs.getString("Event"));
					event.setType(rs.getInt("TYPE"));
					event.setList_dataNum(rs.getInt("number"));
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


	//引数list_numを渡して、list_dataテーブルからそのlist_num中のtrueの値がついているevent_numをlist<Integer>で返す
	public List<Integer> rewardCheck (int list_num){
		Connection conn = null;
		List<Integer> list = new ArrayList<>();
		int event_num;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM LIST_DATA  where list_num = ? and check_tf =true;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, list_num);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				event_num = rs.getInt("Event_num");

				list.add(event_num);
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			list = null;
		}
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					list = null;
				}
			}

		// 結果を返す
		return list;
	}

	//リストデータに入っているCheck_tfがTRUEのイベントのポイントを合計する
	public int Total_P(Date date,String id) {
		int point = 0;

		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");


				// SQL文を準備する
				String sql = "SELECT sum(POINT) as point FROM EVENTS_LEVEL left join EVENTS on CODE  = EVENTS.LEVEL left join LIST_DATA on LIST_DATA.EVENT_NUM  = EVENTS.NUMBER where list_data.CHECK_TF =true and  CHECK_DATE  = ? and user_id = ?";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setDate(1,date);
				pStmt.setString(2,id);



				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					point = rs.getInt("Point");

					};



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


		return point;

	}

	//合計点を出す
	public int Total_P(int list_num) {
		int point = 0;

		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");


				// SQL文を準備する
				String sql = "SELECT sum(POINT) as point FROM EVENTS_LEVEL left join EVENTS on CODE  = EVENTS.LEVEL left join LIST_DATA on LIST_DATA.EVENT_NUM  = EVENTS.NUMBER where list_data.CHECK_TF =true and list_num =?";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1,list_num);
				;



				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					point = rs.getInt("Point");

					};



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


		return point;

	}
}


