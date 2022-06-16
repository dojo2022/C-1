package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import model.UserFavoriteImg;
import model.UserFavoriteVoice;

public class UsersDAO {

	//新規登録メソッド
	public boolean userRegist(String id, String pass, String userName) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO user(id,pass,user_name) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,id);
			pStmt.setString(2,pass);
			pStmt.setString(3,userName);
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


	//ログインのメソッド
	public boolean isLoginOK(User user) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SELECT文を準備する
			String sql = "select count(*) from User where ID = ? and PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getId());
			pStmt.setString(2,user.getPass());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}


	//ユーザ名変更メソッド
	public boolean nameUpdate(User user) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			String sql = "UPDATE USER SET user_name = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, user.getUser_name());

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

		return result;
	}


	//password変更メソッド
	public Boolean passUpdate(User user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			String sql = "UPDATE USER SET pass = ? WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (user.getPass() != null && !user.getPass().equals("")) {
				pStmt.setString(1, user.getPass());
			}
			else {
				pStmt.setString(1, null);
			}

			pStmt.setString(2, user.getId());

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


	//推し画像新規登録
	public boolean imgRegist(String user_id) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO user_favorite_img(user_id) VALUES(?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1,user_id);

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



	//推し画像更新メソッド
	public Boolean imgUpdate(UserFavoriteImg userFavoriteImg) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			String sql = "UPDATE user_favorite_img SET favorite_good_img = ?, favorite_bad_img = ?, favorite_other_img = ? WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (userFavoriteImg.getUser_id() != null && !userFavoriteImg.getUser_id().equals("")) {
				pStmt.setString(1, userFavoriteImg.getUser_id());
			}
			else {
				pStmt.setString(1, null);
			}

			if (userFavoriteImg.getFavorite_good_img() != null && !userFavoriteImg.getFavorite_good_img().equals("")) {
				pStmt.setString(2, userFavoriteImg.getFavorite_good_img());
			}
			else {
				pStmt.setString(2, null);
			}

			if (userFavoriteImg.getFavorite_bad_img() != null && !userFavoriteImg.getFavorite_bad_img().equals("")) {
				pStmt.setString(3, userFavoriteImg.getFavorite_bad_img());
			}
			else {
				pStmt.setString(3, null);
			}

			if (userFavoriteImg.getFavorite_other_img() != null && !userFavoriteImg.getFavorite_other_img().equals("")) {
				pStmt.setString(4, userFavoriteImg.getFavorite_other_img());
			}
			else {
				pStmt.setString(4, null);
			}

			//SQL文を実行
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


	//推しボイス新規登録
		public boolean voiceRegist(String user_id) {
			boolean result = false;
			Connection conn = null;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備する
				String sql = "INSERT INTO user_favorite_voice(user_id) VALUES(?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1,user_id);

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



		//推し画像更新メソッド
		public Boolean imgUpdate(UserFavoriteVoice userFavoriteVoice) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				String sql = "UPDATE user_favorite_voice SET favorite_good_voice = ?, favorite_bad_voice = ?, favorite_other_voice = ? WHERE user_id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				if (userFavoriteVoice.getUser_id() != null && !userFavoriteVoice.getUser_id().equals("")) {
					pStmt.setString(1, userFavoriteVoice.getUser_id());
				}
				else {
					pStmt.setString(1, null);
				}

				if (userFavoriteVoice.getFavorite_good_voice() != null && !userFavoriteVoice.getFavorite_good_voice().equals("")) {
					pStmt.setString(2, userFavoriteVoice.getFavorite_good_voice());
				}
				else {
					pStmt.setString(2, null);
				}

				if (userFavoriteVoice.getFavorite_bad_voice() != null && !userFavoriteVoice.getFavorite_bad_voice().equals("")) {
					pStmt.setString(3, userFavoriteVoice.getFavorite_bad_voice());
				}
				else {
					pStmt.setString(3, null);
				}

				if (userFavoriteVoice.getFavorite_other_voice() != null && !userFavoriteVoice.getFavorite_other_voice().equals("")) {
					pStmt.setString(4, userFavoriteVoice.getFavorite_other_voice());
				}
				else {
					pStmt.setString(4, null);
				}

				//SQL文を実行
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
