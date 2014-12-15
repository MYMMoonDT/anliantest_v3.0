'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Department
 * @description
 * # Department
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Department', function Department($resource) {

    return $resource('api/department/:departmentId', {}, {
      update: {
        method: 'PUT'
      },
      query: {
        method: 'GET',
        url: 'api/department/all',
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
