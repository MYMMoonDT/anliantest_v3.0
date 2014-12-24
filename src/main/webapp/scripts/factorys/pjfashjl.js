'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.PJFASHJL
 * @description
 * # PJFASHJL
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('PJFASHJL', function PJFASHJL($resource) {
    return $resource('api/pjfashjl/:pjfashjlTableId',{},{
      project: {
        method: 'GET',
        url: 'api/pjfashjl/project'
      },
      sign: {
        method: 'POST',
        url: 'api/pjfashjl/sign'
      }
    });
  });
