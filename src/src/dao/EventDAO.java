package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Events;


public class EventDAO {

	//イベントの検索（表示）
	public List<Events> eventSelect(String id) {
		Connection conn = null;
		List<Events> eventsList = new ArrayList<Events>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "select * from Events WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
				pStmt.setString(1, id);

			//検索条件にタイプと難易度の記載があるが、INTをStringに変える必要あり？


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Events event = new Events(
				rs.getInt("number"),
				rs.getString("event"),
				rs.getInt("type"),
				rs.getInt("level"),
				rs.getInt("available"),
				rs.getString("user_id")
				);
				eventsList.add(event);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			eventsList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			eventsList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					eventsList = null;
				}
			}
		}

		// 結果を返す
		return eventsList;
	}


	//イベントの登録
	public boolean eventRegist(String event, int type, int level, String user_id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO events(event,type,level,user_id) VALUES(?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,event);
			pStmt.setInt(2,type);
			pStmt.setInt(3,level);
			pStmt.setString(4,user_id);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}

			catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				result = false;

			}
			finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					result = false;
				}
			  }
		    }
			//結果を返す
			return result;
		}


	//イベントの編集（更新）

	public boolean eventEdit(Events events) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE events set event=?,type=?,level=?,available=? where NUMBER=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,events.getEvent());
			pStmt.setInt(2,events.getType());
			pStmt.setInt(3,events.getLevel());
			pStmt.setInt(4,events.getAvailable());
			pStmt.setInt(5,events.getNumber());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}

			catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				result = false;

			}
			finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					result = false;
				}
			  }
		    }
			//結果を返す
			return result;
		}
	//引数にInteger型Listをいれて、listの中に入っているevent_numに対応するevents_levelとポイントを取得
	public List<Integer> pointCheck(List<Integer> clearEventNum) {
		Connection conn = null;
		List<Integer> list = new ArrayList<>();
		int point = 0;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			for(int i = 0 ; i<clearEventNum.size() ;i++) {
				int clear = clearEventNum.get(i);
				// SQL文を準備する
				String sql = "SELECT number,event,events.level,code,point FROM EVENTS  inner join events_level on events.level"
						+ " = events_level.code where number = ?;";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1,clear);
	;
				//検索条件にタイプと難易度の記載があるが、INTをStringに変える必要あり？


				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					point=rs.getInt("point");
					list.add(point);
				}
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




}


