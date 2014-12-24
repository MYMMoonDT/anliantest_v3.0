'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('MainCtrl', function ($scope, $rootScope, $location, dialogs, EmployeeService, Task, Project, HTPSJL, GZRWD, KHZLDJD, XCDCJL, PJFA, PJFASHJL) {
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
          CREATE_GZRWD(task);
          break;
        case 'APPOINT_XMFZR':
          APPOINT_XMFZR(task);
          break;
        case 'CREATE_KHZLDJD':
          CREATE_KHZLDJD(task);
          break;
        case 'CREATE_XCDCJL':
          CREATE_XCDCJL(task);
          break;
        case 'UPLOAD_PJFA':
          UPLOAD_PJFA(task);
          break;
        case 'CREATE_PJFASHJL':
          CREATE_PJFASHJL(task);
          break;
        case 'SIGN_PJFASHJL':
          SIGN_PJFASHJL(task);
          break;
        case 'CREATE_JCTZD':
          CREATE_JCTZD(task);
          break;
      }
    };

    function CREATE_HTPSJL(task) {
      var dialog = dialogs.create('template/at-htpsjl-dialog.html', 'HtpsjlDialogCtrl', {
        project: task.project
      }, 
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

    function CREATE_GZRWD(task) {
      var dialog = dialogs.create('template/at-gzrwd-dialog.html', 'GzrwdDialogCtrl', {
        project: task.project
      }, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        var gzrwd = new GZRWD();

        angular.extend(gzrwd, data.item);

        gzrwd.issueEmployee = {
          id: $rootScope.employee.id
        };

        gzrwd.$save({taskId: task.id, employeeId: $rootScope.employee.id}, function() {
          refreshData();
        });

      }, function () {
        
      });
    }

    function APPOINT_XMFZR(task) {
      var dialog = dialogs.create('template/at-xmfzr-dialog.html', 'XmfzrDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        var project = new Project();

        project.$appoint({taskId: task.id, employeeId: $rootScope.employee.id, appointEmployeeId: data.id}, function() {
          refreshData();
        });

      }, function () {
        
      });
    }

    function CREATE_KHZLDJD(task) {
      var dialog = dialogs.create('template/at-khzldjd-dialog.html', 'KhzldjdDialogCtrl', {
        project: task.project
      }, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        var khzldjd = new KHZLDJD();

        angular.extend(khzldjd, data.item);

        khzldjd.$save({taskId: task.id, employeeId: $rootScope.employee.id}, function() {
          refreshData();
        });

      }, function () {
        
      });
    }

    function CREATE_XCDCJL(task) {
      var dialog = dialogs.create('template/at-xcdcjl-dialog.html', 'XcdcjlDialogCtrl', {
        project: task.project
      }, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        var xcdcjl = new XCDCJL();

        angular.extend(xcdcjl, data.item);

        xcdcjl.$save({taskId: task.id, employeeId: $rootScope.employee.id}, function() {
          refreshData();
        });

      }, function () {
        
      });
    }

    function UPLOAD_PJFA(task) {
      var dialog = dialogs.create('template/at-pjfa-dialog.html', 'PjfaDialogCtrl', {
        project: task.project
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        var pjfa = new PJFA();

        angular.extend(pjfa, data.item);

        pjfa.$save({taskId: task.id, employeeId: $rootScope.employee.id}, function() {
          refreshData();
        });
      }, function () {
        
      });
    }

    function CREATE_PJFASHJL(task) {
      var dialog = dialogs.create('template/at-pjfashjl-dialog.html', 'PjfashjlDialogCtrl', {
        project: task.project
      }, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        var pjfashjl = new PJFASHJL();

        angular.extend(pjfashjl, data.item);

        pjfashjl.$save({taskId: task.id, employeeId: $rootScope.employee.id}, function() {
          refreshData();
        });
      }, function () {
        
      });
    }

    function SIGN_PJFASHJL(task) {
      var dialog = dialogs.create('template/at-sign-pjfashjl-dialog.html', 'SignPjfashjlDialogCtrl', {
        project: task.project,
        saveText: '签字'
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        var pjfashjl = new PJFASHJL();

        angular.extend(pjfashjl, data.item);

        pjfashjl.employee = {
          id: $rootScope.employee.id
        };

        pjfashjl.$sign({taskId: task.id, employeeId: $rootScope.employee.id}, function() {
          refreshData();
        });
      }, function () {
        
      });
    }

    function CREATE_JCTZD(task) {
      var dialog = dialogs.create('template/at-jctzd-dialog.html', 'JctzdDialogCtrl', {
        project: task.project
      }, 
      {
        size: 'lg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        
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

    $scope.showProjectDetailDialog = function(project) {
      dialogs.create('template/at-project-detail-dialog.html', 'projectDetailDialogCtrl', {
        item: project
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
    };
  });
