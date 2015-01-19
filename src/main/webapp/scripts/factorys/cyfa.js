'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.CYFA
 * @description
 * # CYFA
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('CYFA', function CYFA($resource) {
    return $resource('api/cyfa/:cyfaTableId',{}, {
      project: {
        method: 'GET',
        url: 'api/cyfa/project'
      },
      confirm: {
        method: 'POST',
        url: 'api/cyfa/confirm'
      }
    });
  });