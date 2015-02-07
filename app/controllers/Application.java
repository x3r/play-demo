package controllers;

import models.Task;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public static Form<Task> taskForm = Form.form(Task.class);

	public static Result index() {
		return redirect(routes.Application.tasks());
	}

	public static Result tasks() {
		return ok(index.render(Task.all(), taskForm));
	}

	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(index.render(Task.all(), filledForm));
		} else {
			Task.create(filledForm.get());
			return ok(index.render(Task.all(), taskForm));
		}
	}

	public static Result deleteTask(long id) {
		Task.delete(id);
		return ok(index.render(Task.all(), taskForm));
	}
}
