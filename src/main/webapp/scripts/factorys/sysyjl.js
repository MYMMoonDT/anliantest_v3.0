'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.SYSYJL
 * @description
 * # SYSYJL
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('SYSYJL', function SYSYJL($resource) {
    return $resource('api/sysyjl/:sysyjlTableId',{},{
      project: {
        method: 'GET',
        url: 'api/sysyjl/project'
      },
      confirm: {
        method: 'POST',
        url: 'api/sysyjl/confirm'
      }
    });
  });