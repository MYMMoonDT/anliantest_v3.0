'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:JctzdDialogCtrl
 * @description
 * # JctzdDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('JctzdDialogCtrl', function ($scope, $modalInstance, data, dialogs, ZYBWHYS) {
    $scope.data = data;

    loadAllZYBWHYSItem();

    $scope.data.item = {
      tableNum: 'ALJC/JL32-06',
      revisionStatus: '1/0',
      testStartDate: new Date(),
      testEndDate: new Date(),

      notifyEmployee: null,
      reviewEmployee: null,
      receiveEmployee: null,

      submitDate:  new Date(),
      receiveDate: new Date(),

      items: []
    };

    $scope.addWorkshopPosition = function() {
      var workshopPosition = {
        workshopPosition: '',
        items: []
      };
      $scope.data.item.items.push(workshopPosition);
    };

    $scope.addZYBWHYSItem = function(item) {
      var zybwhysItem = {
        zybwhysItem: null,
        sampleCount: 0
      };
      item.items.push(zybwhysItem);
    };

    $scope.selectEmployee = function(type){
      var dialog = dialogs.create('template/at-select-employee-dialog.html', 'SelectEmployeeDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        switch(type) {
          case 'notify':
            $scope.data.item.notifyEmployee = data;
          break;
          case 'review':
            $scope.data.item.reviewEmployee = data;
          break;
          case 'receive':
            $scope.data.item.receiveEmployee = data;
          break;
        }
      }, function () {
        
      });
    }

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };

    function loadAllZYBWHYSItem() {
      var zybwhys = new ZYBWHYS();
      zybwhys.$query(function(data){
        $scope.zybwhysList = data.data;
      });
    }
  });
