package controllers;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Liked;
import play.mvc.Http;
import play.mvc.Http.Response;

public class ApplicationTest extends ControllersTest {

   private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);

   @Test
   public void testThatIndexPageWorks() {
      Response response = GET("/");
      assertIsOk(response);
      assertContentType("text/html", response);
      assertCharset("utf-8", response);
   }

   @Test
   public void testHomePageWorks() {
      Response response = GET("/home");
      assertIsOk(response);
      assertContentType("text/html", response);
      assertCharset("utf-8", response);
   }
}