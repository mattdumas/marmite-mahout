package controllers;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}