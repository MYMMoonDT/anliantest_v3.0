'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.KHZLDJD
 * @description
 * # KHZLDJD
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('KHZLDJD', function KHZLDJD($resource) {
    return $resource('api/khzldjd/:khzldjdTableId',{},{
      project: {
        method: 'GET',
        url: 'api/khzldjd/project'
      }
    });
  });
