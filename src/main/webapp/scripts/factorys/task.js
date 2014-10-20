'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Task
 * @description
 * # Task
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Task', function Task($resource) {
    return $resource('api/task/:taskId', {}, {
      query: {
        method: 'GET',
        transformResponse: function(data) {
          data = eval('(' + data + ')');
          if(data.callStatus == 'SUCCEED') {
            return data;
          }else{
            return null;
          }
        }
      }
    });
  });
