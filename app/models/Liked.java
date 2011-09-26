package models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Transient;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Jean-Baptiste Lemée
 */
@Entity
public class Liked extends Model {

   @Required
   @MinSize(3)
   @MaxSize(100)
   public String name;

   @MaxSize(255)
   @Required
   public String description;

   @Transient
   public Boolean liked;

   public String toString() {
      return name;
   }

   public static Collection<Liked> fill(Collection<Liked> likedList, User user) {
      for (Liked item : likedList) {
         fill(item, user);
      }
      return likedList;
   }

   public static Liked fill(Liked liked, User user) {
      if (user != null) {
         liked.liked = user.likedList.contains(liked);
      }
      return liked;
   }
}
