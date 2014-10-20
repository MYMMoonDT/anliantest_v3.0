'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:ProjectCtrl
 * @description
 * # ProjectCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('ProjectCtrl', function ($scope, $rootScope, $location, dialogs, Project, HTPSJL) {

    $scope.currPageNum = 1;
    $scope.totalItemNum = 0;
    $scope.pageChanged = function() {
      refreshData();
    };

    refreshData();

    $scope.showAddProjectDialog = function () {
      var dialog = dialogs.create('template/at-project-dialog.html', 'projectDialogCtrl', 
      {
        type: 'CREATE'
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
        
        var project = new Project();

        angular.extend(project, data.item);
        
        project.$save(function(){
          refreshData();

          var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', 
          {
            text: '是否立即创建合同评审记录?',
            type: 'CREATE'
          }, 
          {
            size: 'sm',
            keyboard: true,
            backdrop: 'static',
            windowClass: 'model-overlay'
          });
          dialog.result.then(function () {

            var dialog = dialogs.create('template/at-htpsjl-dialog.html', 'HtpsjlDialogCtrl', {}, 
            {
              size: 'lg',
              keyboard: true,
              backdrop: 'static',
              windowClass: 'model-overlay'
            });

            dialog.result.then(function (data) {
              data.item.project = {
                id: project.id
              };

              var htpsjl = new HTPSJL();

              angular.extend(htpsjl, data.item);

              htpsjl.$save({employeeId: $rootScope.employee.id});

            }, function () {
              
            });

          }, function () {
            
          });

        });

      }, function () {

      });
    };

    $scope.editProject = function (project) {
      var dialog = dialogs.create('template/at-project-dialog.html', 'projectDialogCtrl', 
      {
        type: 'EDIT',
        item: project
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
        var project = new Project();

        angular.extend(project, data.item);
        
        project.$update(function(){
          refreshData();
        });
      }, function () {

      });
    };
    
    $scope.removeProject = function (project) {
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', 
      {
        text: '确定要删除该项目?',
        type: 'DELETE'
      }, 
      {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function () {
        var _project = new Project();

        _project.$delete({projectId:project.id}, function(){
          refreshData();
        });

      }, function () {
      });
    };

    $scope.viewProject = function (project) {
      $location.path('/project/' + project.id);
    };

    function refreshData() {
      Project.query({currPageNum:$scope.currPageNum}, function(data) {
        if(data != null) {
          $scope.projectList = data.data;
          $scope.currPageNum = data.currPageNum;
          $scope.totalItemNum = data.totalItemNum;
        }
      });
    };
  })
  .controller('projectDialogCtrl', function ($scope, $rootScope, $modalInstance, data) {
    $scope.data = data;

    if ($scope.data.type == 'CREATE') {
      $scope.data.item = {
        name: '',
        number: '',
        type: null,

        companyName: '',
        companyAddress: '',
        contactPreson: '',
        contactTel: '',
        contractAmount: '',

        businessEmployee: $rootScope.employee
      };
    } else if ($scope.data.type == 'EDIT') {
      
    }

    $scope.type = null;
    $scope.typeList = [
      {
        value: 'JSXMZYBWHKZXGPJ',
        name: '建设项目职业病危害控制效果评价'
      }
    ];
    if ($scope.data.type == 'EDIT') {
      for (var i = 0; i < $scope.typeList.length; i++) {
        if ($scope.data.item.type == $scope.typeList[i].value) {
          $scope.type = $scope.typeList[i];
          break;
        }
      }
    }

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $scope.data.item.type = $scope.type.value;
      $modalInstance.close($scope.data);
    };
  });

