'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Employee
 * @description
 * # Employee
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Employee', function Employee($resource) {
    return $resource('api/employee/:employeeId', {}, {
      
    });
  });
