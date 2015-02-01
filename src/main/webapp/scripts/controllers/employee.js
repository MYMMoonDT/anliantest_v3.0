'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:EmployeeCtrl
 * @description
 * # EmployeeCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('EmployeeCtrl', function ($scope, dialogs, Employee) {
    
    $scope.currPageNum = 1;
    $scope.totalItemNum = 0;
    $scope.pageChanged = function() {
      refreshData();
    };

    //refreshData();
    loadAllData();

    $scope.showAddEmployeeDialog = function () {
      var dialog = dialogs.create('template/at-employee-dialog.html', 'employeeDialogCtrl', 
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
        var employee = new Employee();

        angular.extend(employee, data.item);
        
        employee.$save(function(){
          refreshData();
        });
      }, function () {

      });
    };

    $scope.editEmployee = function (employee) {
      var dialog = dialogs.create('template/at-employee-dialog.html', 'employeeDialogCtrl', 
      {
        type: 'EDIT',
        item: employee
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
        var employee = new Employee();

        angular.extend(employee, data.item);
        
        employee.$update(function(){
          refreshData();
        });
      }, function () {

      });
    };
    
    $scope.removeEmployee = function (employee) {
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', 
      {
        text: '确定要删除该用户?',
        type: 'DELETE'
      }, 
      {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function () {
        var _employee = new Employee();

        _employee.$delete({employeeId:employee.id}, function(){
          refreshData();
        });

      }, function () {
      });
    };  

    function refreshData() {
      Employee.query({currPageNum:$scope.currPageNum}, function(data) {
        if(data != null) {
          $scope.employeeList = data.data;
          $scope.currPageNum = data.currPageNum;
          $scope.totalItemNum = data.totalItemNum;
        }
      });
    }

    function loadAllData() {
      var employee = new Employee();
      employee.$all(function(data) {
        if(data != null) {
          $scope.employeeList = data.data;
        }
      });
    }
  })
  .controller('employeeDialogCtrl', function ($scope, $modalInstance, data) {
    $scope.data = data;

    if ($scope.data.type == 'CREATE') {
      $scope.data.item = {
        name: '',
        number: '',
        password: '',
        title: '',
        department: null
      };
    } else if ($scope.data.type == 'EDIT') {

    }

    $scope.department = null;
    $scope.departmentList = [
      {
        id: '1',
        name: '市场部'
      },
      {
        id: '2',
        name: '评价部'
      },
      {
        id: '3',
        name: '检测部'
      },
      {
        id: '4',
        name: '行政部'
      },
      {
        id: '5',
        name: '质控部'
      },
      {
        id: '6',
        name: '总经理'
      },
      {
        id: '7',
        name: '技术负责人'
      },
      {
        id: '8',
        name: '质量负责人'
      }
    ];
    if ($scope.data.type == 'EDIT') {
      for (var i = 0; i < $scope.departmentList.length; i++) {
        if ($scope.data.item.department.id == $scope.departmentList[i].id) {
          $scope.department = $scope.departmentList[i];
          break;
        }
      }
    }

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      if($scope.department != null) {
        $scope.data.item.department = $scope.department;
      }
      $modalInstance.close($scope.data);
    };
  });
