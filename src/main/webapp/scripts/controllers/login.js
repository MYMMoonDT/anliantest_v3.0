'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('LoginCtrl', function ($scope, EmployeeService) {
    $scope.employee = {
      number: '',
      password: ''
    };

    $scope.login = function() {
      var promise = EmployeeService.login($scope.employee.number, $scope.employee.password);
      promise.then(function (data){
      }, function (){
      });
    };
  });
