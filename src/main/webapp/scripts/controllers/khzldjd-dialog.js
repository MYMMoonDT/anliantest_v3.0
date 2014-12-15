'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:KhzldjdDialogCtrl
 * @description
 * # KhzldjdDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('KhzldjdDialogCtrl', function ($scope, $modalInstance, data, dialogs, Files) {
    $scope.data = data;

    $scope.data.item = {
      tableNum: 'ALJC/JL32-02',
      revisionStatus: '1/0',
      items: []
    };

    loadResourceList();

    $scope.addItem = function () {
      var item = {
        resource: null,
        submitDate: new Date(),
        resourceNum: 0,
        returnDate: new Date(),
        receiveEmployee: '',
        returnEmployee: ''
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
      for(var i = 0; i < $scope.data.item.items.length; i++) {
        $scope.data.item.items[i].resource = {
          id: $scope.data.item.items[i].resource.id
        };
      }
      $modalInstance.close($scope.data);
    };

    function loadResourceList() {
      var files = new Files();
      files.$list({projectId : data.project.id}, function(data){
        $scope.resourceList = data.data;
      });
    }
  });
