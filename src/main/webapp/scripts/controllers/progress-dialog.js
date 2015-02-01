'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:ProgressDialogCtrl
 * @description
 * # ProgressDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('ProgressDialogCtrl', function ($scope, $modalInstance, ProgressService, data, HTPSJL, GZRWD, KHZLDJD, XCDCJL, PJFA, PJFASHJL, JCTZD, CYFA, SYSYJL, JCBG, JSJG, JGPJ) {
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

      var jctzd = new JCTZD();
      jctzd.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          type: 'jctzd',
          label: '检测通知单'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          table.status = '完成';
        }

        $scope.tableList.push(table);
      });
    } else if($scope.data.stepId == 4) {    //4.项目检测环节
      var cyfa = new CYFA();
      cyfa.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          type: 'cyfa',
          label: '采样方案'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          if(table.data.confirm) {
            table.status = '完成';
          }else{
            table.status = '未确认';
          }
        }
        $scope.tableList.push(table);
      });
    } else if($scope.data.stepId == 5) {    //5.项目实验环节
      var sysyjl = new SYSYJL();
      sysyjl.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          type: 'sysyjl',
          label: '送样收样记录'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          if(table.data.confirm) {
            table.status = '完成';
          }else{
            table.status = '未确认';
          }
        }
        $scope.tableList.push(table);
      });

      var jcbg = new JCBG();
      jcbg.$project({projectId : $scope.data.item.id}, function(data){
        
        var table = {
          data: null,
          type: 'jcbg',
          label: '检测报告'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          if(table.data.confirm) {
            table.status = '完成';
          }else{
            table.status = '未确认';
          }
        }
        $scope.tableList.push(table);
      });
    } else if($scope.data.stepId == 6) {    //6.项目数据处理
      var jsjg = new JSJG();
      jsjg.$project({projectId : $scope.data.item.id}, function(data){
        var table = {
          data: null,
          type: 'jsjg',
          label: '计算过程表'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          table.status = '完成';
        }

        $scope.tableList.push(table);
      });
        
      var jgpj = new JGPJ();
      jgpj.$project({projectId : $scope.data.item.id}, function(data){
        var table = {
          data: null,
          type: 'jgpj',
          label: '结果与判定表'
        };
        table.data = data.data;

        if(table.data == null) {
          table.status = '未创建';
        }else{
          table.status = '完成';
        }

        $scope.tableList.push(table);
      });
    }

    $scope.downloadTable = function(table) {
      if(table.type) {
        window.location.href = "api/" + table.type + "/download?projectId=" + table.data.project.id;
      }
    };
  });
