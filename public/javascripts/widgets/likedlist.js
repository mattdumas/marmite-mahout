require.def("widgets/likedlist", ["jquery", "utils", "widgets/twostatesbutton"], function($, Utils, TwoStatesButton) {

   return {
      "Instance": function (containerId, resource, data, isLikedResource, switchlikeResource) {
         var self = this;

         self.refresh = function(newData) {
            if (newData) {
               data = newData;
            }
            $.ajax({
               url: resource,
               data: data,
               success: onSuccess.curry(containerId, isLikedResource, switchlikeResource),
               error: onError.curry(containerId)
            });
         }

         var onSuccess = function(containerId, isLikedResource, switchLikeResource, likedList) {
            if (likedList.length == 0) {
               $('#' + containerId).html('<p>No data.</p>');
            } else {
               $('#' + containerId).html('<ul id="' + containerId + '-list"></ul>');
               $(likedList).each(function(i, el) {
                  var likeOrUnlikeButton = "";
                  var ignoreButton = "";
                  likeOrUnlikeButton = " <a class='connected' id='" + containerId + "-list-a-" + el.id + "' href='#'></a> ";
                  ignoreButton = " <a class='connected' id='" + containerId + "-list-ignore-" + el.id + "' href='#'></a> ";
                  var ahref = $('<a>').addClass('histolink').attr('href', '#!/liked/' + el.id).attr('alt', el.description).text(el.name);
                  var li = $('<li>').attr('id', containerId + '-li-' + el.id);
                  $(li).append(ahref).append(likeOrUnlikeButton).append(ignoreButton);
                  $('#' + containerId + '-list').append(li);
                  TwoStatesButton.Instance(containerId + "-list-a-" + el.id, 'unlike', 'like', switchLikeResource, {likedId:el.id}, 'liked', el.liked, 'likebutton' + el.id);
               });

            }
         };

         var onError = function(containerId, xhr) {
            $('#' + containerId).html('<p>' + xhr.responseText + '</p>');
         };

         self.refresh();

         $("#" + containerId + "-refresh").click(function(e) {
            e.preventDefault();
            self.refresh();
         });

         $("#" + containerId + "-next").click(function(e) {
            e.preventDefault();
            data.startIndex++;
            self.refresh(data);
         });

         $("#" + containerId + "-prev").click(function(e) {
            e.preventDefault();
            data.startIndex--;
            self.refresh(data);
         });
      }
   };

})