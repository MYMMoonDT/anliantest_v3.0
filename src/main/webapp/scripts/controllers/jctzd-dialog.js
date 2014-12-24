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
