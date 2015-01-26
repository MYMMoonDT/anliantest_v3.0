'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:JcbgDialogCtrl
 * @description
 * # JcbgDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('JcbgDialogCtrl', function ($scope, $rootScope, $modalInstance, data, dialogs, JCBG) {
    var DEFAULT_ZYBWHYS_TD_HEIGHT = 135;
    var DEFAULT_TESTDATE_TD_HEIGHT = 110;
    var DEFAULT_SAMPLE_TD_HEIGHT = 55;

    $scope.data = data;

    $scope.data.items = [];

    loadJCBGTable();

    $scope.save = function() {
      var canSubmit = true;
      for(var i = 0; i < $scope.data.items.length; i++) {
        for(var j = 0; j < $scope.data.items[i].list.length; j++) {
          for(var k = 0; k < $scope.data.items[i].list[j].list.length; k++) {
            for(var p = 0; p < $scope.data.items[i].list[j].list[k].list.length; p++) {
              if($scope.data.items[i].list[j].list[k].list[p].result == undefined ||
                 $scope.data.items[i].list[j].list[k].list[p].result == null ||
                 $scope.data.items[i].list[j].list[k].list[p].result == '' ||
                 $scope.data.items[i].list[j].list[k].list[p].touchTime == undefined ||
                 $scope.data.items[i].list[j].list[k].list[p].touchTime == null ||
                 $scope.data.items[i].list[j].list[k].list[p].touchTime == '' ||
                 $scope.data.items[i].list[j].list[k].list[p].collectTime == undefined ||
                 $scope.data.items[i].list[j].list[k].list[p].collectTime == null ||
                 $scope.data.items[i].list[j].list[k].list[p].collectTime == '') {
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

      var jcbg = new JCBG();

      var list = [];
      for(var i = 0; i < $scope.data.items.length; i++) {
        for(var j = 0; j < $scope.data.items[i].list.length; j++) {
          var item = {
            workshopPosition: $scope.data.items[i].workshopPosition,
            zybwhysItem: $scope.data.items[i].list[j].zybwhysItem.chineseName,
            zybwhysItemDetailName: $scope.data.items[i].list[j].zybwhysItemDetailName,
            testDate: [],
            sampleNum: [],
            result: [],
            touchTime: [],
            collectTime: []
          };

          for(var k = 0; k < $scope.data.items[i].list[j].list.length; k++) {
            item.testDate.push($scope.data.items[i].list[j].list[k].testDate);
            var sampleNum = [];
            var result = [];
            var touchTime = [];
            var collectTime = [];
            for(var p = 0; p < $scope.data.items[i].list[j].list[k].list.length; p++) {
              sampleNum.push($scope.data.items[i].list[j].list[k].list[p].sampleNum);
              result.push($scope.data.items[i].list[j].list[k].list[p].result);
              touchTime.push($scope.data.items[i].list[j].list[k].list[p].touchTime);
              collectTime.push($scope.data.items[i].list[j].list[k].list[p].collectTime);
            }
            item.sampleNum.push(sampleNum);
            item.result.push(result);
            item.touchTime.push(touchTime);
            item.collectTime.push(collectTime);
          }
          list.push(item);
        }
      }

      angular.extend(jcbg, {list:list});

      jcbg.$save({taskId: $scope.data.task.id, employeeId: $rootScope.employee.id}, function(data){
        if(data.callStatus == 'FAILED') {
          var dialog = dialogs.create('template/at-alert-dialog.html', 'AlertCtrl', 
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
          $modalInstance.close($scope.data);
        }
      });
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };

    function loadJCBGTable() {
      var jcbg = new JCBG();
      jcbg.$project({projectId: $scope.data.project.id}, function(data){

        for(var i = 0; i < data.data.items.length; i++) {
          var index = 0;
          for(index = 0; index < $scope.data.items.length; index++) {
            if($scope.data.items[index].workshopPosition == data.data.items[i].workshopPosition) {
              $scope.data.items[index].listTmp.push(data.data.items[i]);
              break;
            }
          }
          if(index == $scope.data.items.length) {
            var workshopPosition = {
              workshopPosition: '',
              listTmp: []
            };
            workshopPosition.workshopPosition = data.data.items[i].workshopPosition;
            workshopPosition.listTmp.push(data.data.items[i]);
            $scope.data.items.push(workshopPosition);
          }
        }

        for(var i = 0; i < $scope.data.items.length; i++) {
          $scope.data.items[i].list = [];
          for(var j = 0; j < $scope.data.items[i].listTmp.length; j++) {
            var index = 0;
            for(index = 0; index < $scope.data.items[i].list.length; index++) {
              if($scope.data.items[i].list[index].zybwhysItem.chineseName == $scope.data.items[i].listTmp[j].zybwhysItem.chineseName) {
                $scope.data.items[i].list[index].listTmp.push($scope.data.items[i].listTmp[j]);
                break;
              }
            }
            if(index == $scope.data.items[i].list.length) {
              var zybwhysItem = {
                zybwhysItem: null,
                zybwhysItemDetailName: '',
                height: DEFAULT_ZYBWHYS_TD_HEIGHT,
                listTmp: []
              };
              zybwhysItem.zybwhysItem = $scope.data.items[i].listTmp[j].zybwhysItem;
              zybwhysItem.zybwhysItemDetailName = $scope.data.items[i].listTmp[j].zybwhysItemDetailName;
              zybwhysItem.listTmp.push($scope.data.items[i].listTmp[j]);
              $scope.data.items[i].list.push(zybwhysItem);
            }
          }
          delete $scope.data.items[i].listTmp;
        }
        
        for(var i = 0; i < $scope.data.items.length; i++) {
          for(var j = 0; j < $scope.data.items[i].list.length; j++) {
            $scope.data.items[i].list[j].list = [];
            for(var k = 0; k < $scope.data.items[i].list[j].listTmp.length; k++) {
              var index = 0;
              for(index = 0; index < $scope.data.items[i].list[j].list.length; index++) {
                if($scope.data.items[i].list[j].list[index].testDate == $scope.data.items[i].list[j].listTmp[k].testDate) {
                  $scope.data.items[i].list[j].list[index].listTmp.push($scope.data.items[i].list[j].listTmp[k]);
                  break;
                }
              }
              if(index == $scope.data.items[i].list[j].list.length) {
                var testDate = {
                  testDate: null,
                  height: DEFAULT_TESTDATE_TD_HEIGHT,
                  listTmp: []
                };
                testDate.testDate = $scope.data.items[i].list[j].listTmp[k].testDate;
                testDate.listTmp.push($scope.data.items[i].list[j].listTmp[k]);
                $scope.data.items[i].list[j].list.push(testDate);
              }
            }
            delete $scope.data.items[i].list[j].listTmp;
          }
        }

        for(var i = 0; i < $scope.data.items.length; i++) {
          for(var j = 0; j < $scope.data.items[i].list.length; j++) {
            for(var k = 0; k < $scope.data.items[i].list[j].list.length; k++) {
              $scope.data.items[i].list[j].list[k].list = [];
              for(var p = 0; p < $scope.data.items[i].list[j].list[k].listTmp.length; p++) {
                var sample = {
                  sampleNum: $scope.data.items[i].list[j].list[k].listTmp[p].sampleNum,
                  result: $scope.data.items[i].list[j].list[k].listTmp[p].result,
                  touchTime: $scope.data.items[i].list[j].list[k].listTmp[p].touchTime,
                  collectTime: $scope.data.items[i].list[j].list[k].listTmp[p].collectTime,
                  height: DEFAULT_SAMPLE_TD_HEIGHT
                }
                $scope.data.items[i].list[j].list[k].list.push(sample);
              }
              delete $scope.data.items[i].list[j].list[k].listTmp;
            }
          }
        }

        for(var i = 0; i < $scope.data.items.length; i++) {
          for(var j = 0; j < $scope.data.items[i].list.length; j++) {
            for(var k = 0; k < $scope.data.items[i].list[j].list.length; k++) {
              var height = 0;
              for(var p = 0; p < $scope.data.items[i].list[j].list[k].list.length; p++) {
                height += $scope.data.items[i].list[j].list[k].list[p].height;
              }
              $scope.data.items[i].list[j].list[k].height = height > $scope.data.items[i].list[j].list[k].height ? height : $scope.data.items[i].list[j].list[k].height;
            }
          }
        }

        for(var i = 0; i < $scope.data.items.length; i++) {
          for(var j = 0; j < $scope.data.items[i].list.length; j++) {
            var height = 0;
            for(var k = 0; k < $scope.data.items[i].list[j].list.length; k++) {
              height += $scope.data.items[i].list[j].list[k].height;
            }
            $scope.data.items[i].list[j].height = height > $scope.data.items[i].list[j].height ? height : $scope.data.items[i].list[j].list[k].height;
          }
        }

      });
    }
  });
