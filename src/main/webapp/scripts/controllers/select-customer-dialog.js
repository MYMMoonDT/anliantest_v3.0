'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:SelectCustomerDialogCtrl
 * @description
 * # SelectCustomerDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('SelectCustomerDialogCtrl', function ($scope, $modalInstance, dialogs, data, Customer) {
    $scope.data = data;

    $scope.currPageNum = 1;
    $scope.totalItemNum = 0;
    $scope.pageChanged = function() {
      refreshData();
    };

    refreshData();

    $scope.selectCustomer = function (customer) {
      $modalInstance.close(customer);
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };

    function refreshData() {
      Customer.query({currPageNum:$scope.currPageNum}, function(data) {
        if(data != null) {
          $scope.customerList = data.data;
          $scope.currPageNum = data.currPageNum;
          $scope.totalItemNum = data.totalItemNum;
        }
      });
    }
  });
