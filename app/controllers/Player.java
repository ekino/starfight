package controllers;

import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.ekino.animation.devoxx.RestPlayerAlgorithm;
import com.ekino.animation.devoxx.model.World;
import com.ekino.animation.devoxx.model.actions.ActionList;

import core.PlayerAlgorithmDefault;

public class Player extends Controller {

	private static final Logger logger = LoggerFactory.getLogger(Player.class);
	private static final RestPlayerAlgorithm playerAlgorithm = new PlayerAlgorithmDefault();

	public static Result ping() {
		String pseudo = playerAlgorithm.ping();
		logger.debug("pseudo : {}", pseudo);
		return ok(pseudo);
	}

	@BodyParser.Of(BodyParser.Json.class)
	@JsonDeserialize(as = ArrayList.class, contentAs = String.class)
	public static Result turn() {
		JsonNode jsonRequest = request().body().asJson();
		logger.debug("json request : {}", jsonRequest);

		if (jsonRequest == null) {
			return badRequest("Missing request boby [World]");
		}

		World world = Json.fromJson(jsonRequest, World.class);
		ActionList actions = playerAlgorithm.turn(world);

		logger.debug("actions lists : {}", actions);

		return ok(Json.toJson(actions));
	}
}
