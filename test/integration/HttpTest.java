package integration;

import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.util.Collections;

import org.codehaus.jackson.JsonNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.Play;
import play.libs.Json;

import com.ekino.animation.devoxx.model.World;
import com.ekino.animation.devoxx.model.actions.ActionList;
import com.ekino.animation.devoxx.model.army.Ship;
import com.ekino.animation.devoxx.model.base.Square;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class HttpTest {

	private static final int PORT = 3333;
	private static String KEY;

	@Before
	public void setUp() {
		RestAssured.port = PORT;
	}

	@After
	public void tearDown() {
		RestAssured.reset();
	}

	@Test
	public void keyNull() {
		running(testServer(PORT), new Runnable() {
			public void run() {
				RestAssured.expect().statusCode(401).when().get("/player/ping");
			}
		});
	}

	@Test
	public void keyWrong() {
		running(testServer(PORT), new Runnable() {
			public void run() {
				RestAssured.expect().statusCode(403).when().get("/player/ping?key=titi");
			}
		});
	}

	@Test
	public void identity() {
		running(testServer(PORT), new Runnable() {
			public void run() {
				KEY = Play.application().configuration().getString("application.secret");
				String body = RestAssured.expect().statusCode(200).when().get("/player/ping?key=" + KEY).andReturn()
						.body().asString();

				play.Logger.info("identity json : " + body);
				Assert.assertNotNull(body);
			}
		});
	}

	@Test
	public void turn() {
		running(testServer(PORT), new Runnable() {
			public void run() {
				KEY = Play.application().configuration().getString("application.secret");
				World world = new World(500, 600, Collections.<Square> emptyList(), 1, Collections.<Ship> emptyList());
				String body = RestAssured.given().contentType(ContentType.JSON).content(world).expect().statusCode(200)
						.when().post("/player/turn?key=" + KEY).andReturn().body().asString();

				JsonNode node = Json.parse(body);
				ActionList actionList = Json.fromJson(node, ActionList.class);
				play.Logger.info("actionList : " + actionList.getActions());
				Assert.assertNotNull("the parsing Json is not OK", actionList.getActions());
			}
		});
	}
}
