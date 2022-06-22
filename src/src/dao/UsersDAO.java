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

			String sql = "UPDATE USER SET user_name = ? WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (user.getUser_name() != null && !user.getUser_name().equals("")) {
				pStmt.setString(1, user.getUser_name());
			}
			else {
				pStmt.setString(1, null);
			}
			pStmt.setString(2, user.getId());

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




	//推し画像のデフォルト表示用のセレクト
	public UserFavoriteImg imgSelect(String user_id) {
		Connection conn = null;
		UserFavoriteImg img = new UserFavoriteImg();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM user_favorite_img WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (user_id != null) {
				pStmt.setString(1, user_id);
			}
			else {
				pStmt.setString(1, "%");
			}



			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {


				img.setUser_id(rs.getString("user_id"));
				img.setFavorite_good_img(rs.getString("favorite_good_img"));
				img.setFavorite_bad_img(rs.getString("favorite_bad_img"));
				img.setFavorite_other_img(rs.getString("favorite_other_img"));

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
		return img;
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

			if (userFavoriteImg.getFavorite_good_img() != null && !userFavoriteImg.getFavorite_good_img().equals("")) {
				pStmt.setString(1, userFavoriteImg.getFavorite_good_img());
			}
			else {
				pStmt.setString(1, null);
			}

			if (userFavoriteImg.getFavorite_bad_img() != null && !userFavoriteImg.getFavorite_bad_img().equals("")) {
				pStmt.setString(2, userFavoriteImg.getFavorite_bad_img());
			}
			else {
				pStmt.setString(2, null);
			}

			if (userFavoriteImg.getFavorite_other_img() != null && !userFavoriteImg.getFavorite_other_img().equals("")) {
				pStmt.setString(3, userFavoriteImg.getFavorite_other_img());
			}
			else {
				pStmt.setString(3, null);
			}
			pStmt.setString(4, userFavoriteImg.getUser_id());

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



	//推しボイスのデフォルト表示用のセレクト
	public UserFavoriteVoice voiceSelect(String user_id) {
		Connection conn = null;
		UserFavoriteVoice voice = new UserFavoriteVoice();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM user_favorite_voice WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (user_id != null) {
				pStmt.setString(1, user_id);
			}
			else {
				pStmt.setString(1, "%");
			}



			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {


				voice.setUser_id(rs.getString("user_id"));
				voice.setFavorite_good_voice(rs.getString("favorite_good_voice"));
				voice.setFavorite_bad_voice(rs.getString("favorite_bad_voice"));
				voice.setFavorite_other_voice(rs.getString("favorite_other_voice"));

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
		return voice;
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



		//推しボイス更新メソッド
		public Boolean voiceUpdate(UserFavoriteVoice userFavoriteVoice) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				String sql = "UPDATE user_favorite_voice SET favorite_good_voice = ?, favorite_bad_voice = ?, favorite_other_voice = ? WHERE user_id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				if (userFavoriteVoice.getFavorite_good_voice() != null && !userFavoriteVoice.getFavorite_good_voice().equals("")) {
					pStmt.setString(1, userFavoriteVoice.getFavorite_good_voice());
				}
				else {
					pStmt.setString(1, null);
				}

				if (userFavoriteVoice.getFavorite_bad_voice() != null && !userFavoriteVoice.getFavorite_bad_voice().equals("")) {
					pStmt.setString(2, userFavoriteVoice.getFavorite_bad_voice());
				}
				else {
					pStmt.setString(2, null);
				}

				if (userFavoriteVoice.getFavorite_other_voice() != null && !userFavoriteVoice.getFavorite_other_voice().equals("")) {
					pStmt.setString(3, userFavoriteVoice.getFavorite_other_voice());
				}
				else {
					pStmt.setString(3, null);
				}
				pStmt.setString(4, userFavoriteVoice.getUser_id());

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


		//ユーザ設定用のセレクト
		public User userSelect(String id) {
			Connection conn = null;
			User user = new User();


			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM user WHERE id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (id != null) {
					pStmt.setString(1,id);
				}
				else {
					pStmt.setString(1, "%");
				}



				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					user.setId(rs.getString("id"));
					user.setPass(rs.getString("pass"));
					user.setUser_name(rs.getString("user_name"));
					user.setReward(rs.getInt("reward"));
					user.setPoint(rs.getInt("point"));
					user.setIcon(rs.getString("icon"));


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
			return user;
		}


		//ユーザ設定：アイコン、ユーザネーム更新メソッド
		public Boolean userUpdate(User user) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				String sql = "UPDATE user SET user_name = ?, icon = ? WHERE id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				if (user.getUser_name() != null && !user.getUser_name().equals("")) {
					pStmt.setString(1, user.getUser_name());
				}
				else {
					pStmt.setString(1, null);
				}

				if (user.getIcon() != null && !user.getIcon().equals("")) {
					pStmt.setString(2, user.getIcon());
				}
				else {
					pStmt.setString(2, null);

				}
				pStmt.setString(3, user.getId());

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

		//ユーザーポイント更新用のメソッド
		public boolean pointUpdate(int point, String id) {

			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備
				String sql = "UPDATE user SET point = ? WHERE ID = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成
				pStmt.setInt(1,point);
				pStmt.setString(2,id);

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
