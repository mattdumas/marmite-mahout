package utils.paging;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class Paging {

   public static <T> PagedContent<T> perform(final List<T> result, final int pageNumber, final int pageSize) {
      if (CollectionUtils.isEmpty(result)) {
         return new PagedContent<T>(result, 0, pageNumber, pageSize);
      } else {
         int from = (pageNumber - 1) * pageSize;
         if (result.size() < pageSize) {
            if (from > result.size()) {
               return new PagedContent<T>(Collections.<T>emptyList(), result.size(), pageNumber, pageSize);
            }
         }
         int to = Math.min(result.size(), pageNumber * pageSize);
         if (from > to || from < 0 || to < 0) {
            return new PagedContent<T>(Collections.<T>emptyList(), result.size(), pageNumber, pageSize);
         }
         return new PagedContent<T>(result.subList(from, to), result.size(), pageNumber, pageSize);

      }
   }
}
