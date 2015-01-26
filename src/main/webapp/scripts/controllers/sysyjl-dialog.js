'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:SysyjlDialogCtrl
 * @description
 * # SysyjlDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('SysyjlDialogCtrl', function ($scope, $rootScope, $modalInstance, data, dialogs, CYFA, SYSYJL) {
    var DEFAULT_TD_HEIGHT = 85;

    $scope.data = data;

    if($scope.data.type == 'CREATE') {
      $scope.data.item = {
        tableNum: 'ALJC/JL27-01-02',
        revisionStatus: '1/0',
        sampleCompanyName: $scope.data.project.customer.companyName,
        sampleCompanyAddress: $scope.data.project.companyAddress,
        sampleName: '',
        sendSampleEmployee: $rootScope.employee,
        receiveSampleEmployee: null,

        sampleStartDate: null,
        sampleEndDate: null,
        sendSampleDate: new Date(),
        receiveSampleDate: new Date(),
        items: []
      };

      loadCYFATable();
    }else if($scope.data.type == 'CONFIRM') {
      loadSYSYJLTable();
    }

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
          case 'send':
            $scope.data.item.sendSampleEmployee = data;
          break;
          case 'receive':
            $scope.data.item.receiveSampleEmployee = data;
          break;
        }
      }, function () {
        
      });
    };

    $scope.selectReceiveEmployee = function(cyjcffItem) {
      var dialog = dialogs.create('template/at-select-employee-dialog.html', 'SelectEmployeeDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        cyjcffItem.receiveEmployee = data;
      }, function () {
        
      });
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      var errMsg = "";
      var canSubmit = true;

      if($scope.data.type == 'CREATE') {
        if($scope.data.item.sampleName == null || $scope.data.item.sampleName == '') {
          canSubmit = false;
          errMsg = "没有输入样品名称";
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

        if($scope.data.item.receiveSampleEmployee == null) {
          canSubmit = false;
          errMsg = "没有指定实验室总收样人";
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

        for(var i = 0; i < $scope.cyfa.items.length; i++) {
          for(var j = 0; j < $scope.cyfa.items[i].items.length; j++) {
            for(var k = 0; k < $scope.cyfa.items[i].items[j].items.length; k++) {
              var sampleCount = 0;
              for(var p = 0; p < $scope.data.item.items.length; p++) {
                sampleCount +=  parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleCount);
              }
              if(sampleCount != parseInt($scope.cyfa.items[i].items[j].items[k].sampleCount)) {
                errMsg = $scope.cyfa.items[i].workshopPosition + "-" + $scope.cyfa.items[i].items[j].zybwhysItem.chineseName + "样品数量不正确";
                canSubmit = false;
                break;
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

        for(var day = 0; day < $scope.data.item.items.length; day++) {
          for(var i = 0; i < $scope.data.item.items[day].items.length; i++) {
            for(var j = 0; j < $scope.data.item.items[day].items[i].items.length; j++) {
              for(var k = 0; k < $scope.data.item.items[day].items[i].items[j].items.length; k++) {
                var sampleNumStart = 1;
                var sampleNumEnd = 0;
                for(var p = 0; p < j; p++) {
                  sampleNumStart += parseInt($scope.data.item.items[day].items[i].items[p].sampleCount);
                }
                for(var p = 0; p < k; p++) {
                  for(var q = 0; q < $scope.data.item.items.length; q++) {
                    sampleNumStart += parseInt($scope.data.item.items[q].items[i].items[j].items[p].sampleCount);
                  }
                }
                for(var p = 0; p < day; p++) {
                  sampleNumStart += parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleCount);
                }
                sampleNumEnd = sampleNumStart + parseInt($scope.data.item.items[day].items[i].items[j].items[k].sampleCount) - 1;
                if((sampleNumStart != parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleNumStart)) ||
                   (sampleNumEnd != parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleNumEnd))) {
                  canSubmit = false;
                  errMsg = $scope.data.item.items[day].items[i].workshopPosition + "-" + $scope.data.item.items[day].items[i].items[j].zybwhysItem.chineseName + "样品编号不正确";
                  break;
                }
              }
              if(!canSubmit)
                break;
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

        $scope.data.item.sampleStartDate = $scope.data.item.sampleStartDate.getTime();
        $scope.data.item.sampleEndDate = $scope.data.item.sampleEndDate.getTime();

        for(var day = 0; day < $scope.data.item.items.length; day++) {
          $scope.data.item.items[day].sampleDate = $scope.data.item.items[day].sampleDate.getTime();
          for(var i = 0; i < $scope.data.item.items[day].items.length; i++) {
            for(var j = 0; j < $scope.data.item.items[day].items[i].items.length; j++) {
              delete $scope.data.item.items[day].items[i].items[j].height;
              for(var k = 0; k < $scope.data.item.items[day].items[i].items[j].items.length; k++) {
                delete $scope.data.item.items[day].items[i].items[j].items[k].height;
              }
            }
          }
        }

        $modalInstance.close($scope.data);
      }else if($scope.data.type == 'CONFIRM') {

        for(var day = 0; day < $scope.data.item.items.length; day++) {
          for(var i = 0; i < $scope.data.item.items[day].items.length; i++) {
            for(var j = 0; j < $scope.data.item.items[day].items[i].items.length; j++) {
              var sampleCount = 0;
              for(var k = 0; k < $scope.data.item.items[day].items[i].items[j].items.length; k++) {
                for(var p = 0; p < $scope.data.item.items.length; p++) {
                  sampleCount +=  parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleCount);
                }
              }
              if(sampleCount != parseInt($scope.data.item.items[day].items[i].items[j].sampleCount)) {
                errMsg = $scope.data.item.items[day].items[i].workshopPosition + "-" + $scope.data.item.items[day].items[i].items[j].zybwhysItem.chineseName + "样品数量不正确";
                canSubmit = false;
                break;
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

        for(var day = 0; day < $scope.data.item.items.length; day++) {
          for(var i = 0; i < $scope.data.item.items[day].items.length; i++) {
            for(var j = 0; j < $scope.data.item.items[day].items[i].items.length; j++) {
              for(var k = 0; k < $scope.data.item.items[day].items[i].items[j].items.length; k++) {
                var sampleNumStart = 1;
                var sampleNumEnd = 0;
                for(var p = 0; p < j; p++) {
                  sampleNumStart += parseInt($scope.data.item.items[day].items[i].items[p].sampleCount);
                }
                for(var p = 0; p < k; p++) {
                  for(var q = 0; q < $scope.data.item.items.length; q++) {
                    sampleNumStart += parseInt($scope.data.item.items[q].items[i].items[j].items[p].sampleCount);
                  }
                }
                for(var p = 0; p < day; p++) {
                  sampleNumStart += parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleCount);
                }
                sampleNumEnd = sampleNumStart + parseInt($scope.data.item.items[day].items[i].items[j].items[k].sampleCount) - 1;
                if((sampleNumStart != parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleNumStart)) ||
                   (sampleNumEnd != parseInt($scope.data.item.items[p].items[i].items[j].items[k].sampleNumEnd))) {
                  canSubmit = false;
                  errMsg = $scope.data.item.items[day].items[i].workshopPosition + "-" + $scope.data.item.items[day].items[i].items[j].zybwhysItem.chineseName + "样品编号不正确";
                  break;
                }
              }
              if(!canSubmit)
                break;
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

        for(var day = 0; day < $scope.data.item.items.length; day++) {
          for(var i = 0; i < $scope.data.item.items[day].items.length; i++) {
            for(var j = 0; j < $scope.data.item.items[day].items[i].items.length; j++) {
              delete $scope.data.item.items[day].items[i].items[j].height;
              for(var k = 0; k < $scope.data.item.items[day].items[i].items[j].items.length; k++) {
                delete $scope.data.item.items[day].items[i].items[j].items[k].height;
              }
            }
          }
        }
        
        $modalInstance.close($scope.data);
      }
    };

    function loadCYFATable() {
      var cyfa = new CYFA();
      cyfa.$project({projectId : $scope.data.project.id}, function(data){

        $scope.cyfa = data.data
        $scope.data.item.sampleStartDate = new Date($scope.cyfa.sampleStartDate);
        $scope.data.item.sampleEndDate = new Date($scope.cyfa.sampleEndDate);

        var dayDiff = 1 + parseInt(($scope.data.item.sampleEndDate.getTime() - $scope.data.item.sampleStartDate.getTime())/86400000);
        for(var day = 0; day < dayDiff; day++) {
          var item = {
            sampleDate: new Date($scope.data.item.sampleStartDate.getTime() + day*86400000),
            items: []  
          };

          for(var i = 0; i < $scope.cyfa.items.length; i++) {
            var workshopPosition = {
              workshopPosition: $scope.cyfa.items[i].workshopPosition,
              items: []
            };
            for(var j = 0; j < $scope.cyfa.items[i].items.length; j++) {
              var zybwhysItem = {
                zybwhysItem: $scope.cyfa.items[i].items[j].zybwhysItem,
                zybwhysItemDetailName: $scope.cyfa.items[i].items[j].zybwhysItemDetailName,
                sampleCount: $scope.cyfa.items[i].items[j].sampleCount,
                height: $scope.cyfa.items[i].items[j].items.length * DEFAULT_TD_HEIGHT,
                items: []
              }
              for(var k = 0; k < $scope.cyfa.items[i].items[j].items.length; k++) {
                var cyjcffItem = {
                  cyjcffItem: $scope.cyfa.items[i].items[j].items[k].cyjcffItem,
                  sampleNumBase: $scope.cyfa.items[i].items[j].items[k].sampleNumBase,
                  sampleCount: 0,
                  sampleNumStart: 0,
                  sampleNumEnd: 0,
                  height: DEFAULT_TD_HEIGHT,
                  sampleAtmos: 0,
                  sampleTemperature: 0,
                  sampleVolume: 0,
                  receiveEmployee: null,
                  receiveDate: new Date()
                }
                if(parseInt($scope.cyfa.items[i].items[j].items[k].sampleCount)%dayDiff == 0) {
                  var sampleNumTemp = parseInt($scope.cyfa.items[i].items[j].items[k].sampleCount)/dayDiff;
                  cyjcffItem.sampleCount = sampleNumTemp;
                  cyjcffItem.sampleNumStart = parseInt($scope.cyfa.items[i].items[j].items[k].sampleNumStart) + day*sampleNumTemp;
                  cyjcffItem.sampleNumEnd = cyjcffItem.sampleNumStart + cyjcffItem.sampleCount - 1;
                }else{
                  var extraSampleNum = parseInt($scope.cyfa.items[i].items[j].items[k].sampleCount)%dayDiff;
                  var sampleNumTemp = (parseInt($scope.cyfa.items[i].items[j].items[k].sampleCount) - extraSampleNum)/dayDiff;
                  if((day+1)<=extraSampleNum) {
                    cyjcffItem.sampleNumStart = parseInt($scope.cyfa.items[i].items[j].items[k].sampleNumStart) + day*sampleNumTemp + day;
                  }else{
                    cyjcffItem.sampleNumStart = parseInt($scope.cyfa.items[i].items[j].items[k].sampleNumStart) + day*sampleNumTemp + extraSampleNum;
                  }
                  if((day+1)<=extraSampleNum) {
                    sampleNumTemp++;
                  }
                  cyjcffItem.sampleCount = sampleNumTemp;
                  cyjcffItem.sampleNumEnd = cyjcffItem.sampleNumStart + cyjcffItem.sampleCount - 1;
                }
                zybwhysItem.items.push(cyjcffItem);
              }
              workshopPosition.items.push(zybwhysItem);
            }
            item.items.push(workshopPosition);
          }
          $scope.data.item.items.push(item);
        }
      });
    }

    function loadSYSYJLTable() {
      var sysyjl = new SYSYJL();
      sysyjl.$project({projectId : $scope.data.project.id}, function(data){
        $scope.data.item = data.data;

        for(var day = 0; day < $scope.data.item.items.length; day++) {
          for(var i = 0; i < $scope.data.item.items[day].items.length; i++) {
            for(var j = 0; j < $scope.data.item.items[day].items[i].items.length; j++) {
              $scope.data.item.items[day].items[i].items[j].height = $scope.data.item.items[day].items[i].items[j].items.length * DEFAULT_TD_HEIGHT;
              for(var k = 0; k < $scope.data.item.items[day].items[i].items[j].items.length; k++) {
                $scope.data.item.items[day].items[i].items[j].items[k].height = DEFAULT_TD_HEIGHT;
              }
            }
          }
        }
      });
    }
  });
