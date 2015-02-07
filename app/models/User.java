package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class User extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void insertUser(User user) {
		user.save();
	}

	public static User getUserByName(String userName) {
		List<User> users = userFinder.where().eq("userName", userName)
				.findList();
		return users.size() == 0 ? null : users.get(0);
	}

	public static void editUser(String userName, String password) {
		User user = userFinder.where().eq("userName", userName).findList()
				.get(0);
		user.password = password;
		user.save();
	}

	public static Finder<Long, User> userFinder = new Finder<Long, User>(
			Long.class, User.class);
}
