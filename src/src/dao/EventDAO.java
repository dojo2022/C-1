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
	public List<Events> eventSelect(Events param) {
		Connection conn = null;
		List<Events> eventsList = new ArrayList<Events>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "select number, event, type, level, available, user_id from BC WHERE event LIKE ? AND type LIKE ? AND level LIKE ? ORDER BY NUMBER";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getEvent() != null) {
				pStmt.setString(1, "%" + param.getEvent() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}
			//検索条件にタイプと難易度の記載があるが、INTをStringに変える必要あり？


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Events card = new Events(
				rs.getInt("number"),
				rs.getString("event"),
				rs.getInt("type"),
				rs.getInt("level"),
				rs.getInt("available"),
				rs.getString("user_id")
				);
				eventsList.add(card);
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
			String sql = "UPDATE events set event=?,type=?,level=?,available=?,user_id=? where NUMBER=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,events.getEvent());
			pStmt.setInt(2,events.getType());
			pStmt.setInt(3,events.getLevel());
			pStmt.setInt(4,events.getAvailable());
			pStmt.setString(5,events.getUser_id());

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

}


