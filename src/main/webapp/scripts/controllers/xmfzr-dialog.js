'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:XmfzrDialogCtrl
 * @description
 * # XmfzrDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('XmfzrDialogCtrl', function ($scope, $modalInstance, dialogs, data, Employee) {
    $scope.data = data;

    $scope.currPageNum = 1;
    $scope.totalItemNum = 0;
    $scope.pageChanged = function() {
      refreshData();
    };

    refreshData();

    $scope.appointEmployee = function (employee) {
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', 
      {
        text: '确定要指定该用户?',
        type: 'SIGN'
      }, 
      {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function () {
        $modalInstance.close(employee);
      }, function () {
      });
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
