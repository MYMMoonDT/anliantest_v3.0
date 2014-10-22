'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.GZRWD
 * @description
 * # GZRWD
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('GZRWD', function GZRWD($resource) {
    return $resource('api/gzrwd/:gzrwdTableId');
  });
