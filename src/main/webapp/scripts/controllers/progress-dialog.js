'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:ProgressDialogCtrl
 * @description
 * # ProgressDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('ProgressDialogCtrl', function ($scope, $modalInstance, ProgressService, data, HTPSJL, GZRWD, KHZLDJD, XCDCJL, PJFA, PJFASHJL) {
    $scope.data = data;

    $scope.stepMap = ProgressService.getStepMap();

    for(var step in $scope.stepMap) {
      if(step == $scope.data.item.step) {
        $scope.step = $scope.stepMap[step];
        break;
      }
    }

    $scope.tableList = [];

    for(var step in $scope.stepMap) {
      if($scope.stepMap[step].id == $scope.data.stepId) {
        $scope.tableList.step = $scope.stepMap[step];
        break;
      }
    }

    if($scope.data.stepId == 1) {            //1.项目录入
      var htpsjl = new HTPSJL();
      htpsjl.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          label: '合同评审记录'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          var index = 0;
          for(index = 0; index < table.data.items.length; index++) {
            if(table.data.items[index].employee == null) {
              table.status = '未签字';
              break;
            }
          }
          if(index == table.data.items.length)
            table.status = '完成';
        }

        $scope.tableList.push(table);
      });
    }else if($scope.data.stepId == 2) {      //2.项目下达
      var gzrwd = new GZRWD();
      gzrwd.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          label: '工作任务单'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          table.status = '完成';
        }

        $scope.tableList.push(table);
      });
    }else if($scope.data.stepId == 3) {      //3.项目前期准备
      var khzldjd = new KHZLDJD();
      khzldjd.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          label: '客户资料登记单'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          table.status = '完成';
        }

        $scope.tableList.push(table);
      });

      var xcdcjl = new XCDCJL();
      xcdcjl.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          label: '现场调查记录'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          table.status = '完成';
        }

        $scope.tableList.push(table);
      });

      var pjfa = new PJFA();
      pjfa.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          label: '评价方案'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          table.status = '完成';
        }

        $scope.tableList.push(table);
      });

      var pjfashjl = new PJFASHJL();
      pjfashjl.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          label: '评价方案审核记录'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          var index = 0;
          for(index = 0; index < table.data.items.length; index++) {
            if(table.data.items[index].employee == null) {
              table.status = '未签字';
              break;
            }
          }
          if(index == table.data.items.length)
            table.status = '完成';
        }

        $scope.tableList.push(table);
      });
    }

  });
