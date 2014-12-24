'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.XCDCJL
 * @description
 * # XCDCJL
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('XCDCJL', function XCDCJL($resource) {
    return $resource('api/xcdcjl/:xcdcjlTableId',{},{
      project: {
        method: 'GET',
        url: 'api/xcdcjl/project'
      }
    });
  });
