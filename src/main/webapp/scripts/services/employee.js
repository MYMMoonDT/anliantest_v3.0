'use strict';

/**
 * @ngdoc service
 * @name anliantestApp.EmployeeService
 * @description
 * # EmployeeService
 * Service in the anliantestApp.
 */
angular.module('anliantestApp')
  .service('EmployeeService', function EmployeeService($http, $q, $rootScope, $location, $sessionStorage) {
    var that = this;

    this.getCurrEmployee = function () {
      if($rootScope.employee == undefined || $rootScope.employee == null){
        $rootScope.employee = $sessionStorage.employee;
      }
      if($rootScope.employee == undefined || $rootScope.employee == null){
        $location.path('/login');
      }
      return $rootScope.employee;
    };

    this.saveCurrEmployee = function (employee) {
      $rootScope.employee = employee;
      $sessionStorage.employee = employee;
    };

    this.clearCurrEmployee = function () {
      delete $rootScope.employee;
      delete $sessionStorage.employee;
    };

    this.login = function(number, password) {
      var deferred = $q.defer();
      $http.get('api/employee/login', {params: {'number': number, 'password': password}})
      .success(function (data, status, headers, config){
        if(data.callStatus == 'SUCCEED') {
          that.saveCurrEmployee(data.data);
          $location.path('/');
        }
        deferred.resolve(data);
      })
      .error(function (data, status, headers, config){
        deferred.reject(data);
      });
      return deferred.promise;
    };

    this.logout = function () {
      that.clearCurrEmployee();
      $location.path('/login');
    };
  });
