'use strict';

/**
 * @ngdoc directive
 * @name anliantestApp.directive:atHeader
 * @description
 * # atHeader
 */
angular.module('anliantestApp')
  .directive('atHeader', function () {
    return {
      templateUrl: 'template/at-header.html',
      restrict: 'E',
      controller: function($scope, EmployeeService) {
        $scope.employee = EmployeeService.getCurrEmployee();

        $scope.logout = function() {
          EmployeeService.logout();
        };
      },
      link: function postLink(scope, element, attrs) {
        
      }
    };
  });
