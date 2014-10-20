'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Htpsjl
 * @description
 * # Htpsjl
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('HTPSJL', function HTPSJL($resource) {
    return $resource('api/htpsjl/:htpsjlTableId');
  });
