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
	//➀日付とtypeを受け取り、ランダムに入れたくない要素のnumberをListにいれて返す
	public List<String> notIn(String id,int type,Date date){
		Connection conn = null;
		List<String> noList = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql ="SELECT id FROM (SELECT ROW_NUMBER()OVER(partition by type)No events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE id= ? and type = ? and (check_date > ?)";
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
			String sql = "SELECT count(*)FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE No not in ("+ids+")and id = ? and type =?";

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
	//引数リスト　list:はじきたいNo　x:ランダムの最大数　type:家事仕事とか　q:何個抽出するのか
	public List<Events> random (String id,List<String>list,int x,int type,int q){
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
				String sql = "SELECT * FROM (SELECT ROW_NUMBER()OVER(partition by type)No, events.*,list_data.check_date FROM events left outer join list_data on events.number = list_data.event_num) WHERE No not in ("+ids+") and id = ? and type = ? limit 1 offset ? ";

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
						0,
						0,
						0,
						""
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

}
