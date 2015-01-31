'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:AuthorityCtrl
 * @description
 * # AuthorityCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('AuthorityCtrl', function ($scope, dialogs, Employee) {
    $scope.currentEmployee = null;

    loadAllEmployee();

    $scope.showDefaultAuthorityDialog = function() {
      var dialog = dialogs.create('template/at-default-authority-dialog.html', 'DefaultAuthorityDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
      }, function () {

      });
    };

    $scope.editEmployeeAuthorityGroup = function() {
      var dialog = dialogs.create('template/at-edit-employee-authority-dialog.html', 'editEmployeeAuthorityDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
      dialog.result.then(function (data) {
        var employee = new Employee();
        employee.$updateAuthorityGroup({employeeId: $scope.currentEmployee.id, authorityGroupId: data.id}, function(data){
          loadCurrentEmployee(data.data.id);
        });
      }, function () {

      });
    };

    $scope.editEmployeeAuthorityItem = function(employeeAuthItem) {
      var employee = new Employee();

      angular.extend(employee, employeeAuthItem);

      employee.$updateAuthorityItem(function(){
        loadCurrentEmployee($scope.currentEmployee.id);
      });
    };

    function loadAllEmployee() {
      var employee = new Employee();
      employee.$all(function(data){
        var treeData = [];
        var preDepartment = "";
        for(var i = 0; i < data.data.length; i++) {
          var department = data.data[i].department.name;
          if(department != preDepartment) {
            treeData.push({text: department, icon: "fa fa-users", selectable: false, nodes: []});
            preDepartment = department;
          }
          treeData[treeData.length - 1].nodes.push({
            text: data.data[i].name, 
            icon: "fa fa-user", 
            employeeId: data.data[i].id
          }); 
        }
        var options = {
          collapseIcon: "fa fa-chevron-down",
          expandIcon: "fa fa-chevron-right",
          onNodeSelected: function(event, node) {
            loadCurrentEmployee(node.employeeId);
          }
        };
        $('#employee-tree').treeview({data: treeData});
        $('#employee-tree').treeview(options);
      });
    }

    function loadCurrentEmployee(employeeId){
      if(employeeId != undefined) {
        var employee = new Employee();
        employee.$get({employeeId: employeeId}, function(data){
          $scope.currentEmployee = data.data;
        });
      }
    }
  })

  .controller('editEmployeeAuthorityDialogCtrl', function($scope, $modalInstance, data, dialogs, Authority){
    $scope.data = data;

    loadAllAuthorityGroup();

    $scope.selectAuthorityGroup = function(group) {
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', {
        text: '是否确认将用户变更为该权限组?',
        type: 'SIGN'
      }, {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function() {
        $modalInstance.close(group);
      });
    };

    function loadAllAuthorityGroup() {
      var authority = new Authority();
      authority.$groupAll(function(data) {
        $scope.authorityGroups = data.data;
      });
    }
  });

  /*
    $scope.employee = EmployeeService.getCurrEmployee();
  	$scope.hasAuth = false;
  
    var promise = EmployeeService.getAllEmployees($scope.employee.number, $scope.employee.password);
    promise.then(function (data){
      $scope.employees = data.data;
      var treeData = [];
      var preDep = "";
      for (var i in $scope.employees) {
        var department = $scope.employees[i].department.name
        if (department != preDep) {
          treeData.push({text: department, icon: "fa fa-users", selectable: false, nodes: []});
          preDep = department;
        }
        treeData[treeData.length-1].nodes.push({
          text: $scope.employees[i].name, 
          icon: "fa fa-user", 
          employeeId: $scope.employees[i].id
        });       
      }
      var options = {
          collapseIcon: "fa fa-chevron-down",
          expandIcon: "fa fa-chevron-right",
          onNodeSelected: function(event, node) {
            $scope.$apply(function() {
            	$scope.hasAuth = locate(node.employeeId);
            });
          }
      };

      $('#tree').treeview({data: treeData});
      $('#tree').treeview(options);
    }, function (){
      
    });
    
    function reset(employee) {
    	locate(employee.id, employee);
    }
    
    function locate(employeeId, employee) {
    	for (var i in $scope.employees) {
    		if (employeeId === $scope.employees[i].id) {
    			if (employee != null)
    				$scope.employees[i] = employee;
    			$scope.curAuthEmployee = $scope.employees[i];
    			$scope.authorityGroups = $scope.employees[i].employeeAuthorityGroups;
    			for (var j in $scope.authorityGroups) {
    				if ($scope.authorityGroups[j].isActive) {
    					$scope.authorityItems = $scope.authorityGroups[j].employeeAuthorityGroupItems;
    					$scope.selectedAuthGrp = $scope.authorityGroups[j];
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    $scope.toggleClicked = function() {
    	updateEmployee($scope.curAuthEmployee);
    };
    
    function updateEmployee(employee) {
      var promise = EmployeeService.updateEmployee(employee);
      promise.then(function(ret) {
      	if (ret.errorCode == "No_Error") {
      		reset(ret.data);
      	} else {
      		alert(ret.errorCode);
      	}
      });
    }
    
    $scope.changeAuthrityGroup = function() {
      for (var i in $scope.authorityGroups) {
          $scope.authorityGroups[i].isActive = false;
      }
      $scope.selectedAuthGrp.isActive = true;
      $scope.authorityItems = $scope.selectedAuthGrp.employeeAuthorityGroupItems;
      updateEmployee($scope.curAuthEmployee);
    }
    
    $scope.showEmployeeAuthGrpDialog = function(employee, selectedAuthGrp) {
      var promise = AuthorityService.getAllAuthorityGroups();
      promise.then(function(data) {
        var dialog = dialogs.create('template/at-employee-authgrp-dialog.html', 'employeeAuthGrpDialogCtrl', 
            {
              employee: employee,
              curAuthGrp: selectedAuthGrp,
              groupList: data.data,
            }, 
            {
              size: 'md',
              keyboard: true,
              backdrop: 'static',
              windowClass: 'model-overlay',
            }
        );
        dialog.result.then(function(list) {
          var promise = EmployeeService.updateEmployeeAuthorityGroups(employee, list);
          promise.then(function(ret) {
          	if (ret.errorCode == "No_Error") {
          		reset(ret.data);
          	} else {
          		alert(ret.errorCode);
          	}
          });
        });
      });
    };
  })
      
  .controller('employeeAuthGrpDialogCtrl', function ($scope, $modalInstance, data) {
    $scope.list = [];
    $scope.curAuthGrp = {id: data.curAuthGrp.authorityGroup.id, name: data.curAuthGrp.authorityGroup.name, selected: true};
    $scope.groups = data.employee.employeeAuthorityGroups;
    for (var i in data.groupList) {
      $scope.list.push({id: data.groupList[i].id, name: data.groupList[i].name, selected: false});
    }
    for (var i in $scope.groups) {
    	for (var j in $scope.list) {
			  if ($scope.list[j].id == $scope.groups[i].authorityGroup.id) {
			    $scope.list[j].selected = true;
			    break;
			  }
    	}
    }

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
		};
		
		$scope.save = function() {
		  $modalInstance.close($scope.list);
		};
  });
*/
  