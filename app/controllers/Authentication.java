package controllers;

import models.User;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Authentication extends Controller {
	static Form<User> userForm = Form.form(User.class);

	public static Result loginPage() {
		return ok(login.render(userForm));
	}

	public static Result login() {
		Form<User> form = userForm.bindFromRequest();
		User user = form.get();
		User dbUser = User.getUserByName(user.getUserName());
		if(dbUser==null){
			flash().put("status", "Failure");
			return ok(login.render(userForm));
		}
		if (dbUser.getPassword().matches(user.getPassword())) {
			flash().put("status", "Successful");
			return ok(login.render(userForm));
		} else {
			flash().put("status", "Failure");
			return ok(login.render(userForm));
		}
	}
}
