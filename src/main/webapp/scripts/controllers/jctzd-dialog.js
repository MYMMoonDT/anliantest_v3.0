'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:JctzdDialogCtrl
 * @description
 * # JctzdDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('JctzdDialogCtrl', function ($scope, $modalInstance, $rootScope, data, dialogs, ZYBWHYS, JCTZD) {
    $scope.data = data;

    loadAllZYBWHYSItem();

    $scope.data.item = {
      project: {
        id: $scope.data.project.id
      },
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

    $scope.deleteWorkshopPosition = function(workshopPosition) {
      var index = $scope.data.item.items.indexOf(workshopPosition);
      $scope.data.item.items.splice(index, 1);
    };

    $scope.addZYBWHYSItem = function(item) {
      var zybwhysItem = {
        zybwhysItem: null,
        zybwhysItemDetailName: '',
        sampleCount: 0
      };
      item.items.push(zybwhysItem);
    };

    $scope.deleteZYBWHYSItem = function(workshopPosition, zybwhysItem) {
      var index = workshopPosition.items.indexOf(zybwhysItem);
      workshopPosition.items.splice(index, 1);
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
      var canSubmit = true;
      for(var i = 0; i < $scope.data.item.items.length; i++) {
        if($scope.data.item.items[i].workshopPosition == '') {
          canSubmit = false;
          break;
        }
        for(var j = 0; j < $scope.data.item.items[i].items.length; j++) {
          if($scope.data.item.items[i].items[j].selected == undefined ||
             $scope.data.item.items[i].items[j].selected == null) {
            canSubmit = false;
            break;
          }
        }
        if(!canSubmit)
          break;
      }

      if(!canSubmit) {
        dialogs.create('template/at-alert-dialog.html', 'AlertCtrl', 
        {
          text: '数据输入不完整'
        }, 
        {
          size: 'sm',
          keyboard: true,
          backdrop: 'static',
          windowClass: 'model-overlay'
        });
        return;
      }

      for(var i = 0; i < $scope.data.item.items.length; i++) {
        for(var j = 0; j < $scope.data.item.items[i].items.length; j++) {
          $scope.data.item.items[i].items[j].zybwhysItem = $scope.data.item.items[i].items[j].selected;
          delete $scope.data.item.items[i].items[j].selected;
        }
      }

      var jctzd = new JCTZD();
      angular.extend(jctzd, $scope.data.item);

      jctzd.$save({taskId: $scope.data.task.id, employeeId: $rootScope.employee.id}, function(data) {
        if(data.callStatus == 'FAILED') {
          dialogs.create('template/at-alert-dialog.html', 'AlertCtrl', 
        {
          text: data.errorMsg
        }, 
        {
          size: 'sm',
          keyboard: true,
          backdrop: 'static',
          windowClass: 'model-overlay'
        });
        }else if(data.callStatus == 'SUCCEED') {
          $modalInstance.close();
        }
      });
    };

    function loadAllZYBWHYSItem() {
      var zybwhys = new ZYBWHYS();
      zybwhys.$query(function(data){
        $scope.zybwhysList = data.data;
      });
    }
  });
