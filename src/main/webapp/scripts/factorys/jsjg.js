'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.JSJG
 * @description
 * # JSJG
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('JSJG', function JSJG($resource) {
    return $resource('api/jsjg/:jsjgTableId', {}, {
      
    });
  });
