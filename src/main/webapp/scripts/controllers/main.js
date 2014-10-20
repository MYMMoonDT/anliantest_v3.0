'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('MainCtrl', function ($scope, $rootScope, $location, dialogs, EmployeeService, Task, HTPSJL) {
    $scope.employee = EmployeeService.getCurrEmployee();

    refreshData();

    $scope.finishTask = function (task) {
      switch(task.projectStatus) {
        case 'CREATE_HTPSJL':
          CREATE_HTPSJL(task);
          break;
        case 'SIGN_HTPSJL':
          SIGN_HTPSJL(task);
          break;
        case 'CREATE_GZRWD':
          break;
        case 'APPOINT_XMFZR':
          break;
        case 'CREATE_KHZLDJD':
          break;
        case 'CREATE_XCDCJL':
          break;
      }
    };

    function CREATE_HTPSJL(task) {
      var dialog = dialogs.create('template/at-htpsjl-dialog.html', 'HtpsjlDialogCtrl', {}, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        data.item.project = {
          id: task.project.id
        };
        
        var htpsjl = new HTPSJL();

        angular.extend(htpsjl, data.item);

        htpsjl.$save({employeeId: $rootScope.employee.id}, function() {
          refreshData();
        });
      }, function () {
        
      });
    }

    function SIGN_HTPSJL(task) {
      console.log(task);
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', 
      {
        text: '确定要在合同评审记录上签字?',
        type: 'SIGN'
      }, 
      {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function () {
        var htpsjl = new HTPSJL();

        htpsjl.$sign({taskId : task.id, employeeId : $rootScope.employee.id}, function (){
          refreshData();
        });
      }, function () {
        
      });
    }

    function refreshData() {
      if($rootScope.employee != undefined && $rootScope.employee != null) {
        Task.query({id : $rootScope.employee.id, type : 'employee'}, function(data) {
          if(data != null) {
            $scope.employeeTaskList = data.data;
          }
        });
        Task.query({id: $rootScope.employee.department.id,type:'department'}, function(data) {
          if(data != null) {
            $scope.departmentTaskList = data.data;
          }
        });
      }
    }
  });
