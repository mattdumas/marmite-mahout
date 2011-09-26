package controllers;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.lucene.queryParser.ParseException;

import com.google.common.collect.Sets;
import models.Liked;
import models.User;
import play.Logger;
import play.mvc.Controller;
import services.SearchService;
import utils.paging.Paging;

public class Application extends Controller {

   public static void index() {
      render();
   }

   public static void home() {
      render();
   }

   public static void liked(Long id) {
      Liked liked = findLiked(id);
      if (liked == null) {
         notFound();
      }
      User user = Security.connectedUser();
      Liked.fill(liked, user);
      render(liked);
   }

   public static void search(String text, int startIndex, int pageSize) {
      Set<Liked> likedSet = null;
      try {
         List<Liked> likedList = Paging.perform(SearchService.search(text), startIndex, pageSize).getContent();
         if (isEmpty(likedList)) {
            likedSet = Sets.newHashSet();
         } else {
            likedSet = Sets.newLinkedHashSet(likedList);
         }
      } catch (IOException e) {
         Logger.error(e, e.getMessage());
         error(e.getMessage());
      } catch (ParseException e) {
         error(e.getMessage());
      }
      Liked.fill(likedSet, Security.connectedUser());
      renderJSON(likedSet);
   }

   static Liked findLiked(long itemID) {
      return Liked.findById(itemID);
   }
}