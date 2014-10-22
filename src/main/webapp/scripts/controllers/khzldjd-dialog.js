'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:KhzldjdDialogCtrl
 * @description
 * # KhzldjdDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('KhzldjdDialogCtrl', function ($scope, $modalInstance, data, dialogs) {
    $scope.data = data;

    $scope.data.item = {
      items: []
    };

    $scope.addItem = function () {
      var item = {
        resourceName: '',
        submitDate: new Date(),
        resourceNum: 0,
        returnDate: new Date(),
        receiveEmployee: null,
        returnEmployee: null
      }; 
      $scope.data.item.items.push(item);
    };

    $scope.deleteItem = function (item) {
      var index = $scope.data.item.items.indexOf(item);
      $scope.data.item.items.splice(index, 1);
    };

    $scope.selectReceiveEmployee = function (item) {
      var dialog = dialogs.create('template/at-select-employee-dialog.html', 'SelectEmployeeDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        item.receiveEmployee = data;
      }, function () {
        
      });
    };

    $scope.selectReturnEmployee = function (item) {
      var dialog = dialogs.create('template/at-select-employee-dialog.html', 'SelectEmployeeDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        item.returnEmployee = data;
      }, function () {
        
      });
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
