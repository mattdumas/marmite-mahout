package models;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.test.UnitTest;

import java.util.ArrayList;
import java.util.HashSet;

public class LikedTest extends UnitTest {
   private static final Logger log = LoggerFactory.getLogger(LikedTest.class);
   private HashSet<Liked> likedList;
   private User user;

   @Before
   public void before() {
      user = new User();
      user.email="test@test.com";
      likedList = new HashSet<Liked>();
      Liked iLike = new Liked();
      iLike.id = 1l;
      Liked iDontLike = new Liked();
      iDontLike.id = 2l;
      likedList.add(iLike);
      likedList.add(iDontLike);
      user.likedList = new ArrayList();
      user.likedList.add(iLike);

   }

   @Test
   public void fillWhenUserIsConnected() {
      user.id = 1l;
      Liked.fill(likedList, user);
      for (Liked liked : likedList) {
         if (liked.getId() == 1l) {
            assertTrue(liked.liked);
         } else {
            assertEquals(false, liked.liked);
         }
      }
   }

   @Test
   public void fillWhenUserIsNotConnected() {
      user = null;
      Liked.fill(likedList, user);
      for (Liked liked : likedList) {
         assertNull(liked.liked);
      }
   }
}
