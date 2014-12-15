'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:DepartmentCtrl
 * @description
 * # DepartmentCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('DepartmentCtrl', function ($scope, dialogs, Department) {
    refreshData();

    $scope.editDepartment = function(department) {
      var dialog = dialogs.create('template/at-department-dialog.html', 'departmentDialogCtrl', 
      {
        type: 'EDIT',
        item: department
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
        var department = new Department();

        angular.extend(department, data.item);
        
        department.$update(function(){
          refreshData();
        });
      }, function () {

      });
    };

    function refreshData() {
      Department.query(function(data) {
        if(data != null) {
          $scope.departmentList = data.data;
        }
      });
    }
  })

  .controller('departmentDialogCtrl', function ($scope, $modalInstance, data) {
    $scope.data = data;

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
