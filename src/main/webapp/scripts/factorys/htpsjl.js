'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.HTPSJL
 * @description
 * # HTPSJL
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('HTPSJL', function HTPSJL($resource) {
    return $resource('api/htpsjl/:htpsjlTableId', {}, {
      sign: {
        method: 'POST',
        url: 'api/htpsjl/sign'
      }
    });
  });
