'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:SelectEmployeeDialogCtrl
 * @description
 * # SelectEmployeeDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('SelectEmployeeDialogCtrl', function ($scope, $modalInstance, dialogs, data, Employee) {
    $scope.data = data;

    $scope.currPageNum = 1;
    $scope.totalItemNum = 0;
    $scope.pageChanged = function() {
      refreshData();
    };

    refreshData();

    $scope.selectEmployee = function (employee) {
      $modalInstance.close(employee);
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };

    function refreshData() {
      Employee.query({currPageNum:$scope.currPageNum}, function(data) {
        if(data != null) {
          $scope.employeeList = data.data;
          $scope.currPageNum = data.currPageNum;
          $scope.totalItemNum = data.totalItemNum;
        }
      });
    }
  });
