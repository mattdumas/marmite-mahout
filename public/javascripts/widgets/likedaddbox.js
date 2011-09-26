require.def("widgets/likedaddbox", ["jquery", "utils"], function($, Utils) {
   return {
      "Instance": function (containerId, resource) {

         var self = this;
         self.onLikedAdded = new Utils.Event();

         $('#' + containerId + '-button').live('click', function(e) {
            e.preventDefault();
            var name = $('#' + containerId + '-input-name').val();
            var description = $('#' + containerId + '-input-description').val();
            $.ajax({
               url: resource,
               data: {'liked.name':name, 'liked.description':description},
               success: onSuccess.curry(containerId),
               error: onError.curry(containerId),
               type:'POST'
            });
         });

         var onSuccess = function(containerId, newLiked) {
            self.onLikedAdded.execute(newLiked);
            $('#' + containerId + '-input-name').val("");
            $('#' + containerId + '-input-description').val("");
            $('#' + containerId+"-error").hide();
         }

         var onError = function(containerId, xhr) {
            $('#' + containerId+"-error").html($('#' + containerId+"-error").html()+xhr.responseText);
            $('#' + containerId+"-error").show();
         }
      }
   };

});