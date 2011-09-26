package controllers;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.Http;
import play.mvc.Http.Response;

public class ListsTest extends ControllersTest {

   private static final Logger log = LoggerFactory.getLogger(ListsTest.class);

   // Play 1.3 or play 1.2.2RC1 is needed.
   @Test
   public void testMostLiked() {
      Map<String, String> parameters1 = new HashMap<String, String>();
      parameters1.put("likedId", "1");
      Map<String, String> parameters2 = new HashMap<String, String>();
      parameters2.put("likedId", "2");

      addLiked("number one", "Description one");
      addLiked("number two", "Description two");
      addLiked("number three", "Description three");
      POST("/logout");
      POST(connectedRequest("test3@test3.com"), "/like", parameters1);
      POST(connectedRequest("test3@test3.com"), "/like", parameters2);
      POST("/logout");
      POST(connectedRequest("test2@test2.com"), "/like", parameters2);

      Response response = GET("/liked/most");

      assertIsOk(response);
      assertContentType("application/json; charset=utf-8", response);
      assertCharset("utf-8", response);
   }
}