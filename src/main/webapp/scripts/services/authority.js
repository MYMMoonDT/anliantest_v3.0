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
      $http.get('api/authority/group/all')
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
    
    this.getAllAuthorityItems = function() {
      var deferred = $q.defer();
      $http.get('api/authority/item/all')
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
    
    this.updateAuthorityGroup = function(data) {
      var deferred = $q.defer();
      $http.post('api/authority/group/update', data)
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
    
    this.addAuthorityGroup = function(data) {
      var deferred = $q.defer();
      $http.post('api/authority/group/add', data)
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
    
    this.deleteAuthGrp = function(data) {
    	var deferred = $q.defer();
      $http.delete('api/authority/group/delete/'+data)
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
