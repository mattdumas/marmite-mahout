package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.Logger;
import play.data.validation.Email;
import play.db.jpa.Model;

/**
 * @author Jean-Baptiste Lemée
 */
@Entity
public class User extends Model {

   @Email
   public String email;

   @OneToMany
   public Collection<Liked> likedList;

   public User(Long id) {
      super();
      this.id = id;
   }

   public User() {
      super();
   }

   /**
    * Recherche par email.
    */
   public static User findByMail(String mail) {
      if (mail == null) {
         return null;
      }
      return User.find("from User z where email=:mail").bind("mail", mail.trim()).first();
   }

   public static User findByMailOrCreate(String userEmail) {
      User user = findByMail(userEmail);
      if (user == null) {
         Logger.info("User creation for email : " + userEmail);
         user = new User();
         user.email = userEmail;
         user.save();
      }
      return user;
   }

   @Override
   public String toString() {
      return email;
   }

   public boolean isLiked(Long likedId) {
      if (likedList != null) {
         for (Liked liked : likedList) {
            if (liked.id.equals(likedId)) {
               return true;
            }
         }
      }
      return false;
   }

   public Collection<Long> getAllLikedIds() {
      Collection result = new ArrayList();

      if (likedList != null) {
         for (Liked liked : likedList) {
            result.add(liked.id);
         }
      }
      return result;

   }
}
