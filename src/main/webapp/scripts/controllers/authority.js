'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:AuthorityCtrl
 * @description
 * # AuthorityCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('AuthorityCtrl', function ($scope, $window, dialogs, EmployeeService, AuthorityService) {
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
//            levels: 1,
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
    
//    $scope.updateAuthority = function() {
//    	updateEmployee($scope.curAuthEmployee);
//    };
    
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
//                ret: null,
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
//          		selectedAuthGrp = employye
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
