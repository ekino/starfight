package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result index() {
		return ok(index
				.render("Ekino @Devoxx : Sauver les maîtres Jedi"));
	}

}