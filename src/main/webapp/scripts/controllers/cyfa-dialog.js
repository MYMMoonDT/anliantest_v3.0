'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:CyfaDialogCtrl
 * @description
 * # CyfaDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('CyfaDialogCtrl', function ($scope, $modalInstance, data, dialogs, CYFA) {
    var DEFAULT_TD_HEIGHT = 70;

    $scope.data = data;
    
    $scope.data.item = {
      tableNum: 'ALJC/JL26-01',
      revisionStatus: '1/1',

      sampleStartDate: new Date(),
      sampleEndDate: new Date(),

      planEmployee: null,
      planDate: new Date(),

      reviewEmployee: null,
      reviewDate: new Date()
    };

    loadCYFATable();

    function loadCYFATable() {
      var cyfa = new CYFA();
      cyfa.$project({projectId : $scope.data.project.id}, function(data){

        $scope.data.item.id = data.data.id;
        $scope.data.item.project = data.data.project;

        $scope.data.item.items = data.data.items;
        for(var i = 0; i < $scope.data.item.items.length; i++) {
          for(var j = 0; j < $scope.data.item.items[i].items.length; j++) {
            $scope.data.item.items[i].items[j].height = $scope.data.item.items[i].items[j].items.length * DEFAULT_TD_HEIGHT;
            for(var k = 0; k < $scope.data.item.items[i].items[j].items.length; k++) {
              $scope.data.item.items[i].items[j].items[k].height = DEFAULT_TD_HEIGHT;
            }
          }
        }
      });
    }

    $scope.selectEmployee = function(type) {
      var dialog = dialogs.create('template/at-select-employee-dialog.html', 'SelectEmployeeDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        switch(type) {
          case 'plan':
            $scope.data.item.planEmployee = data;
          break;
          case 'review':
            $scope.data.item.reviewEmployee = data;
          break;
        }
      }, function () {
        
      });
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      var errMsg = "";
      var canSubmit = true;

      for(var i = 0; i < $scope.data.item.items.length; i++) {
        for(var j = 0; j < $scope.data.item.items[i].items.length; j++) {
          var sampleCount = 0;
          for(var k = 0; k < $scope.data.item.items[i].items[j].items.length; k++) {
            sampleCount += parseInt($scope.data.item.items[i].items[j].items[k].sampleCount);
          }
          if(sampleCount != parseInt($scope.data.item.items[i].items[j].sampleCount)){
            canSubmit = false;
            errMsg = $scope.data.item.items[i].workshopPosition + "-" + $scope.data.item.items[i].items[j].zybwhysItem.chineseName + "样品数量不正确";
            break;
          }
        }
        if(!canSubmit)
          break;
      }

      if(!canSubmit) {
        dialogs.create('template/at-alert-dialog.html', 'AlertCtrl', 
        {
          text: errMsg
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
          var sampleNumStart = 1;
          var sampleNumEnd = 0;
          for(var k = 0; k < j; k++) {
            sampleNumStart += parseInt($scope.data.item.items[i].items[k].sampleCount);
          }
          sampleNumEnd = sampleNumStart + parseInt($scope.data.item.items[i].items[k].sampleCount) - 1;
          for(var p = 0; p < $scope.data.item.items[i].items[j].items.length; p++) {
            if(parseInt($scope.data.item.items[i].items[j].items[p].sampleCount) > 0) {
              var sampleSubNumStart = sampleNumStart;
              var sampleSubNumEnd = 0;
              for(var q = 0; q < p; q++) {
                sampleSubNumStart += parseInt($scope.data.item.items[i].items[j].items[q].sampleCount);
              }
              sampleSubNumEnd = sampleSubNumStart + parseInt($scope.data.item.items[i].items[j].items[p].sampleCount) - 1;
              if(sampleSubNumStart != parseInt($scope.data.item.items[i].items[j].items[p].sampleNumStart) ||
                 sampleSubNumEnd != parseInt($scope.data.item.items[i].items[j].items[p].sampleNumEnd)) {
                canSubmit = false;
                errMsg = $scope.data.item.items[i].workshopPosition + "-" + $scope.data.item.items[i].items[j].zybwhysItem.chineseName + "样品编号不正确";
                break;
              }
            }
          }
          if(!canSubmit)
            break;
        }
        if(!canSubmit)
          break;
      }

      if(!canSubmit) {
        dialogs.create('template/at-alert-dialog.html', 'AlertCtrl', 
        {
          text: errMsg
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
          delete $scope.data.item.items[i].items[j].height;
          for(var k = 0; k < $scope.data.item.items[i].items[j].items.length; k++) {
            delete $scope.data.item.items[i].items[j].items[k].height;
          }
        }
      }

      $modalInstance.close($scope.data);
    };
  });
