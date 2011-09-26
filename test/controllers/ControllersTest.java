package controllers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;

import play.Play;
import play.libs.Crypto;
import play.mvc.Http;
import play.test.Fixtures;
import play.test.FunctionalTest;

/**
 * @author Jean-Baptiste lemée
 */
public abstract class ControllersTest extends FunctionalTest {

   @BeforeClass
   public static void init() {
      Play.configuration.setProperty("application.mode", "TEST");
   }

   @Before
   public void setup() {
      Fixtures.deleteDatabase();
      Fixtures.loadModels("data.yml");
   }

   /*
    * force connection for test purpose.
    */
   protected Http.Request connectedRequest() {
      return connectedRequest("test@test.com");
   }

   /*
   * force connection for test purpose.
   */
   protected Http.Request connectedRequest(String username) {
      Http.Request request = newRequest();
      Http.Cookie rememberme = new Http.Cookie();
      rememberme.value = Crypto.sign(username) + "-" + username;
      rememberme.maxAge = 1;
      request.cookies.put("rememberme", rememberme);
      return request;
   }

   protected static Http.Response POST(Http.Request request, String url, Map<String, String> parameters) {
      return POST(request, url, parameters, new HashMap<String, File>());
   }

   public static Http.Response PUT(Http.Request request, Object url, String c) {
      return PUT(request, url, APPLICATION_X_WWW_FORM_URLENCODED, "");
   }


   protected Http.Response addLiked(String likedName, String likedDescription) {
      Map<String, String> parameters = new HashMap<String, String>();
      parameters.put("liked.name", likedName);
      parameters.put("liked.description", likedDescription);
      return POST(connectedRequest(), "/liked", parameters);
   }
}
