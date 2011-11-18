import controllers.Reco;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.BooleanUserPreferenceArray;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.test.UnitTest;

import java.util.List;

public class BasicTest extends UnitTest {

   private static final Logger log = LoggerFactory.getLogger(BasicTest.class);

   @Test
   public void testRecommendation() throws TasteException {
      int howMany = 4;
      List<RecommendedItem> recommendation = Reco._internalRecommend(howMany, USER_1, usersData());
      assertTrue(recommendation.size() <= howMany);
      /*assertEquals(ITEM_?, (Long) recommendation.get(0).getItemID());
      assertEquals(ITEM_?, (Long) recommendation.get(1).getItemID());
      assertEquals(ITEM_?, (Long) recommendation.get(2).getItemID());
      assertEquals(ITEM_?, (Long) recommendation.get(3).getItemID());*/
      fail();
   }

   private static FastByIDMap<PreferenceArray> usersData() {
      FastByIDMap<PreferenceArray> result = new FastByIDMap<PreferenceArray>();

      // User 1
      BooleanUserPreferenceArray preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_1);
      preferenceArray.setItemID(0, ITEM_1);
      preferenceArray.setItemID(1, ITEM_2);
      preferenceArray.setItemID(2, ITEM_3);
      preferenceArray.setItemID(3, ITEM_4);
      preferenceArray.setItemID(4, ITEM_5);
      result.put(USER_1, preferenceArray);

      // User 2
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_2);
      preferenceArray.setItemID(0, ITEM_9);
      preferenceArray.setItemID(1, ITEM_6);
      preferenceArray.setItemID(2, ITEM_7);
      preferenceArray.setItemID(3, ITEM_8);
      preferenceArray.setItemID(4, ITEM_10);
      result.put(USER_2, preferenceArray);

      // User 3
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_3);
      preferenceArray.setItemID(0, ITEM_1);
      preferenceArray.setItemID(1, ITEM_2);
      preferenceArray.setItemID(2, ITEM_3);
      preferenceArray.setItemID(3, ITEM_4);
      preferenceArray.setItemID(4, ITEM_9);
      result.put(USER_3, preferenceArray);


      // User 4
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_4);
      preferenceArray.setItemID(0, ITEM_1);
      preferenceArray.setItemID(1, ITEM_2);
      preferenceArray.setItemID(2, ITEM_3);
      preferenceArray.setItemID(3, ITEM_5);
      preferenceArray.setItemID(4, ITEM_9);
      result.put(USER_4, preferenceArray);

      // User 5
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_5);
      preferenceArray.setItemID(0, ITEM_5);
      preferenceArray.setItemID(1, ITEM_6);
      preferenceArray.setItemID(2, ITEM_7);
      preferenceArray.setItemID(3, ITEM_10);
      preferenceArray.setItemID(4, ITEM_11);
      result.put(USER_5, preferenceArray);

      // User 6
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_6);
      preferenceArray.setItemID(0, ITEM_1);
      preferenceArray.setItemID(1, ITEM_2);
      preferenceArray.setItemID(2, ITEM_3);
      preferenceArray.setItemID(3, ITEM_6);
      preferenceArray.setItemID(4, ITEM_7);
      result.put(USER_6, preferenceArray);


      // User 7
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_7);
      preferenceArray.setItemID(0, ITEM_1);
      preferenceArray.setItemID(1, ITEM_2);
      preferenceArray.setItemID(2, ITEM_3);
      preferenceArray.setItemID(3, ITEM_10);
      preferenceArray.setItemID(4, ITEM_11);
      result.put(USER_7, preferenceArray);

      // User 8
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_8);
      preferenceArray.setItemID(0, ITEM_5);
      preferenceArray.setItemID(1, ITEM_6);
      preferenceArray.setItemID(2, ITEM_7);
      preferenceArray.setItemID(3, ITEM_10);
      preferenceArray.setItemID(4, ITEM_11);
      result.put(USER_8, preferenceArray);

      // User 9
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_9);
      preferenceArray.setItemID(0, ITEM_1);
      preferenceArray.setItemID(1, ITEM_2);
      preferenceArray.setItemID(2, ITEM_3);
      preferenceArray.setItemID(3, ITEM_5);
      preferenceArray.setItemID(4, ITEM_9);
      result.put(USER_9, preferenceArray);


      // User 10
      preferenceArray = new BooleanUserPreferenceArray(5);
      preferenceArray.setUserID(0, USER_10);
      preferenceArray.setItemID(0, ITEM_1);
      preferenceArray.setItemID(1, ITEM_2);
      preferenceArray.setItemID(2, ITEM_3);
      preferenceArray.setItemID(3, ITEM_7);
      preferenceArray.setItemID(4, ITEM_9);
      result.put(USER_10, preferenceArray);

      return result;
   }

   static final Long ITEM_1 = 10l;
   static final Long ITEM_2 = 20l;
   static final Long ITEM_3 = 30l;
   static final Long ITEM_4 = 40l;
   static final Long ITEM_5 = 50l;
   static final Long ITEM_6 = 60l;
   static final Long ITEM_7 = 70l;
   static final Long ITEM_8 = 80l;
   static final Long ITEM_9 = 90l;
   static final Long ITEM_10 = 100l;
   static final Long ITEM_11 = 110l;

   static final long USER_1 = 0l;
   static final long USER_2 = 1l;
   static final long USER_3 = 2l;
   static final long USER_4 = 3l;
   static final long USER_5 = 4l;
   static final long USER_6 = 5l;
   static final long USER_7 = 6l;
   static final long USER_8 = 7l;
   static final long USER_9 = 8l;
   static final long USER_10 = 9l;

}
