package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public List<UserFavoriteImg> imgSelect(UserFavoriteImg param) {
		Connection conn = null;
		List<UserFavoriteImg> imgList = new ArrayList<UserFavoriteImg>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT favorite_good_img, favorite_bad_img, favorite_other_img FROM user_favorite_img WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getUser_id() != null) {
				pStmt.setString(1, "%" + param.getUser_id() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}



			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UserFavoriteImg img = new UserFavoriteImg(
				rs.getInt("number"),
				rs.getString("user_id"),
				rs.getString("favorite_good_img"),
				rs.getString("favorite_bad_img"),
				rs.getString("favorite_other_img")
				);
				imgList.add(img);
			}
		}


		catch (SQLException e) {
			e.printStackTrace();
			imgList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			imgList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					imgList = null;
				}
			}
		}

		// 結果を返す
		return imgList;
	}


	//推しボイスのデフォルト表示用のセレクト
	public List<UserFavoriteVoice> voiceSelect(UserFavoriteVoice param) {
		Connection conn = null;
		List<UserFavoriteVoice> voiceList = new ArrayList<UserFavoriteVoice>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT favorite_good_voice, favorite_bad_voice, favorite_other_voice FROM user_favorite_voice WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getUser_id() != null) {
				pStmt.setString(1, "%" + param.getUser_id() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}



			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UserFavoriteVoice voice = new UserFavoriteVoice(
				rs.getInt("number"),
				rs.getString("user_id"),
				rs.getString("favorite_good_voice"),
				rs.getString("favorite_bad_voice"),
				rs.getString("favorite_other_voice")
				);
				voiceList.add(voice);
			}
		}


		catch (SQLException e) {
			e.printStackTrace();
			voiceList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			voiceList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					voiceList = null;
				}
			}
		}

		// 結果を返す
		return voiceList;
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
		public List<User> userSelect(User param) {
			Connection conn = null;
			List<User> userList = new ArrayList<User>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6_data/C1", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM user WHERE id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (param.getId() != null) {
					pStmt.setString(1, "%" + param.getId() + "%");
				}
				else {
					pStmt.setString(1, "%");
				}



				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					User user = new User(
					rs.getString("id"),
					rs.getString("pass"),
					rs.getString("user_name"),
					rs.getInt("reward"),
					rs.getInt("point"),
					rs.getString("icon")
					);
					userList.add(user);
				}
			}


			catch (SQLException e) {
				e.printStackTrace();
				userList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				userList = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						userList = null;
					}
				}
			}

			// 結果を返す
			return userList;
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
}
