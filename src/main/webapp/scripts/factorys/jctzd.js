'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.JCTZD
 * @description
 * # JCTZD
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('JCTZD', function JCTZD($resource) {
    return $resource('api/jctzd/:jctzdTableId', {}, {
    });
  });
