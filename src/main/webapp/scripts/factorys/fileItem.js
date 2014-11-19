'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Employee
 * @description
 * # Employee
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('FileItem', function FileItem($resource) {


    return $resource('api/project/:projectId', {}, {
      update: {
        method: 'PUT'
      },
      query: {
        method: 'GET',
        params: 
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
