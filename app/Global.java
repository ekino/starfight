import java.lang.reflect.Method;

import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Result;

public class Global extends GlobalSettings {

	@Override
	public Action onRequest(Request request, Method method) {
		String KEY_APPLICATION = Play.application().configuration().getString("application.secret");

		// on ne securise pas la page d'accueil
		if (request.path().equals("/")) {
			return super.onRequest(request, method);
		}

		String key = request.getQueryString("key");
		Logger.debug("key : " + key);

		if (key == null) {
			// return 401
			return new Action.Simple() {
				@Override
				public Result call(Context arg0) throws Throwable {
					return unauthorized("Vous n'avez pas spécifier de clé");
				}
			};
		}
		if (!key.equals(KEY_APPLICATION)) {
			// return 403
			return new Action.Simple() {
				@Override
				public Result call(Context arg0) throws Throwable {
					return forbidden("Votre clé est incorrecte");
				}
			};
		} else {
			return super.onRequest(request, method);
		}

	}
}
