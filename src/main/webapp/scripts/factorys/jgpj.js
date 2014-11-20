'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.JGPJ
 * @description
 * # JGPJ
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('JGPJ', function JGPJ($resource) {
    return $resource('api/jgpj/:jgpjTableId', {}, {
    });
  });
