'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:EmployeeCtrl
 * @description
 * # EmployeeCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('EmployeeCtrl', function ($scope, dialogs, Employee, DTOptionsBuilder, DTColumnDefBuilder) {
    
    $scope.employeeList = Employee.query();

    $scope.dtOptions = DTOptionsBuilder.newOptions().withBootstrap();
    
    $scope.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1),
        DTColumnDefBuilder.newColumnDef(2),
        DTColumnDefBuilder.newColumnDef(3),
        DTColumnDefBuilder.newColumnDef(4).notSortable()
    ];

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

        employee.name = data.item.name;
        employee.number = data.item.number;
        employee.password = data.item.password;
        employee.title = data.item.title;
        employee.department = {
          id: data.item.department.id
        };
        
        employee.$save();
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

        employee.name = data.item.name;
        employee.number = data.item.number;
        employee.password = data.item.password;
        employee.title = data.item.title;
        employee.department = {
          id: data.item.department.id
        };
        
        employee.$save();
      }, function () {

      });
    };
    
    $scope.removeEmployee = function (employee) {

    };  
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
