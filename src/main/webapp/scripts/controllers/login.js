'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('LoginCtrl', function ($scope, employee) {
    $scope.employee = {
      number: '',
      password: ''
    };

    $scope.login = function() {
      employee.login($scope.employee.number, $scope.employee.password);
    };
  });
