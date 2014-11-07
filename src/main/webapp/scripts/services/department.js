'use strict';

/**
 * @ngdoc service
 * @name anliantestApp.department
 * @description
 * # department
 * Service in the anliantestApp.
 */
angular.module('anliantestApp')
  .service('department', function department() {
	this.getAllEmployees = function () {
		var deferred = $q.defer();
	    $http.get('api/department/all')
	    .success(function (data, status, headers, config){
	      if(data.callStatus == 'SUCCEED') {
	        
	      }
	      deferred.resolve(data);
	    })
	    .error(function (data, status, headers, config){
	      deferred.reject(data);
	    });
	    return deferred.promise;
	};
  });
