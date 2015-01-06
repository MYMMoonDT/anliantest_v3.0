'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.JCBG
 * @description
 * # JCBG
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('JCBG', function JCBG($resource) {
    return $resource('api/jcbg/:jcbgTableId', {}, {
      input: {
        method: 'POST',
        url: 'api/jcbg/input'
      }
    });
  });
