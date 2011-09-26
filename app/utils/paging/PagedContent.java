package utils.paging;

import java.util.List;

public class PagedContent<T> {
   private int totalSize;
   private List<T> content;
   private int startIndex;
   private int pageSize;

   public PagedContent(List<T> list) {
      this(list, list.size());
   }

   public PagedContent(List<T> list, int rowCount) {
      this(list, rowCount, -1, -1);
   }

   public PagedContent(List<T> list, int rowCount, int pageNumber, int pageSize) {
      this.content = list;
      this.totalSize = rowCount;
      this.startIndex = pageNumber;
      this.pageSize = pageSize;
   }

   public int getTotalSize() {
      return totalSize;
   }

   public List<T> getContent() {
      return content;
   }

   public int getStartIndex() {
      return startIndex;
   }

   public int getPageSize() {
      return pageSize;
   }
}
