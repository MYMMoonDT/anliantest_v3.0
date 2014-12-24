'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.PJFA
 * @description
 * # PJFA
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('PJFA', function PJFA($resource) {
    return $resource('api/pjfa/:pjfaId',{},{
      project: {
        method: 'GET',
        url: 'api/pjfa/project'
      }
    });
  });
