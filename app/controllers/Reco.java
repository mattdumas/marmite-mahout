package controllers;

import static controllers.Application.findLiked;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.BooleanUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import models.Liked;
import models.User;
import play.Logger;
import play.mvc.Controller;
import play.mvc.With;
import services.CrossingBooleanRecommenderBuilder;
import services.CrossingDataModelBuilder;
import services.SearchService;
import utils.paging.Paging;

@With(Secure.class)
public class Reco extends Controller {

   public static void like(Long likedId) {
      User user = Security.connectedUser();
      if (!user.isLiked(likedId)) {
         doLike(likedId, user);
      }
      renderJSON(Liked.fill(Collections.singleton(findLiked(likedId)), user));
   }

   static void doLike(Long likedId, User user) {
      Liked liked = findLiked(likedId);
      user.likedList.add(liked);
      user.save();
   }

   public static void unlike(Long likedId) {
      User user = Security.connectedUser();
      doUnlike(likedId, user);
      renderJSON(Liked.fill(Collections.singleton(findLiked(likedId)), user));
   }

   static void doUnlike(Long likedId, User user) {
      Liked liked = findLiked(likedId);
      user.likedList.remove(liked);
      user.save();
   }

   public static void switchLike(Long likedId) {
      User user = Security.connectedUser();
      if (user.isLiked(likedId)) {
         doUnlike(likedId, user);
      } else {
         doLike(likedId, user);
      }
      renderJSON(Liked.fill(Collections.singleton(findLiked(likedId)), user));
   }

   public static void addLiked(Liked liked) {
      if (StringUtils.isEmpty(liked.name) || StringUtils.isEmpty(liked.description)) {
         badRequest();
      }
      liked.save();
      User user = Security.connectedUser();
      doLike(liked.id, user);
      try {
         SearchService.addToIndex(liked);
      } catch (IOException e) {
         Logger.error(e, e.getMessage());
      }
      renderJSON(Liked.fill(liked, user));
   }

   public static boolean isLiked(Long likedId) {
      User user = Security.connectedUser();
      return user.isLiked(likedId);
   }

   public static void recommendUser(int startIndex, int pageSize) throws TasteException {
      User user = Security.connectedUser();
      int trainUsersLimit = 100;
      FastByIDMap<PreferenceArray> usersData = usersData(trainUsersLimit);
      usersData.put(user.id, getPreferences(trainUsersLimit++, user.id));
      List<RecommendedItem> recommendedItems = Paging.perform(_internalRecommend(startIndex * pageSize, user.id, usersData), startIndex, pageSize).getContent();
      Set<Liked> likedSet = new LinkedHashSet<Liked>(recommendedItems.size());
      for (RecommendedItem item : recommendedItems) {
         Liked liked = findLiked(item.getItemID());
         if (liked != null) {
            likedSet.add(liked);
         }
      }
      Liked.fill(likedSet, user);
      renderJSON(likedSet);
   }

   public static List<RecommendedItem> _internalRecommend(int howMany, Long userId, FastByIDMap<PreferenceArray> usersData) throws TasteException {
      RecommenderBuilder recommenderBuilder = new CrossingBooleanRecommenderBuilder();
      DataModel trainingModel = new CrossingDataModelBuilder().buildDataModel(usersData);
      Recommender recommender = recommenderBuilder.buildRecommender(trainingModel);
      return recommender.recommend(userId, howMany, null);
   }

   static FastByIDMap<PreferenceArray> usersData(int limit) {
      FastByIDMap<PreferenceArray> result = new FastByIDMap<PreferenceArray>();
      Collection<User> allUsers = User.findAll();
      int numUser = 0;
      for (User user : allUsers) {
         if (numUser > limit) {
            break;
         }
         BooleanUserPreferenceArray preferenceArray = getPreferences(numUser, user.id);
         result.put(user.id, preferenceArray);
         numUser++;
      }

      return result;
   }

   static BooleanUserPreferenceArray getPreferences(int numUser, Long userId) {
      User user = User.findById(userId);
      Collection<Long> likedIds = user.getAllLikedIds();
      BooleanUserPreferenceArray preferenceArray = new BooleanUserPreferenceArray(likedIds.size());
      preferenceArray.setUserID(numUser, userId);
      int numLiked = 0;
      for (Long likedId : likedIds) {
         preferenceArray.setItemID(numLiked++, likedId);
      }
      return preferenceArray;
   }


   protected static boolean userAlreadyExists(User user) {
      return (null != Security.findUser(user.email));
   }
}