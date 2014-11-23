'use strict';

/**
 * @ngdoc service
 * @name anliantestApp.authority
 * @description
 * # authority
 * Service in the anliantestApp.
 */
angular.module('anliantestApp')
  .service('AuthorityService', function AuthorityService($http, $q) {
    this.getAllAuthorityGroups = function() {
      var deferred = $q.defer();
      $http.get('api/authority/allGroups')
        .success(function(data, status, headers, config) {
          if (data.callStatus == 'SUCCEED') {
          }
          deferred.resolve(data);
        })
        .error(function(data, status, headers, config) {
          deferred.reject(data);
        });
      return deferred.promise;
    };
  });
