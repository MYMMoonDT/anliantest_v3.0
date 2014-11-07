'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:AuthorityCtrl
 * @description
 * # AuthorityCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('AuthorityCtrl', function ($scope, EmployeeService) {
	  $scope.employee = EmployeeService.getCurrEmployee();
//	  $scope.list = [{title:"1", items:[{title:"1-1"},{title:"1-2"}]},{title:"2"}];
//	  $('.angular-ui-tree-handle').css('cursor','pointer')
	  
//	  $scope.employees = {};
	  $scope.authorityGroups = 1;
      var promise = EmployeeService.getAllEmployees($scope.employee.number, $scope.employee.password);
      promise.then(function (data){
    	  $scope.employees = data.data;
    	  var treeData = [];
//    	  var employees = data.data;
    	  var preDep = "";
    	  for (var i in $scope.employees) {
//    		  var employee = $scope.employees[i].name;
    		  var department = $scope.employees[i].department.name
    		  if (department != preDep) {
    			  treeData.push({text: department, icon: "fa fa-users", nodes: []});
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
    				  var flag = false;
    				  for (var i in $scope.employees) {
    					  if (node.employeeId === $scope.employees[i].id) {
    						  $scope.curAuthEmployee = $scope.employees[i];
    						  $scope.authorityGroups = $scope.employees[i].employeeAuthorityGroups;
    						  for (var j in $scope.authorityGroups) {
    							  if ($scope.authorityGroups[j].isActive) {
    								  $scope.authorityItems = $scope.authorityGroups[j].employeeAuthorityGroupItems;
    								  flag = true;
    								  break;
    							  }
    						  }
    						  break;
    					  }
    				  }
    				  if (!flag)
    					  $scope.authorityItems = [];
    				  $scope.$digest();
    			  }
    	  };

    	  $('#tree').treeview({data: treeData});
    	  $('#tree').treeview(options);
      }, function (){
    	  
      });
      
      $scope.updateAuthority = function() {
    	  EmployeeService.updateEmployee($scope.curAuthEmployee);  
      }
//      $scope.updateAuth2 = function() { 
//    	  $scope.authorityGroups = $scope.authorityGroups +1;
//      };
      
//      $scope.updateAuth = function(event, node) {
//		  for (var i in $scope.employees) {
//			  if (node.employeeId === $scope.employees[i].id) {
//				  $scope.authorityGroups = $scope.employees[i].employeeAuthorityGroups;
//				  for (var j in $scope.authorityGroups) {
//					  if ($scope.authorityGroups[j].isActive) {
//						  $scope.authorityItems = $scope.authorityGroups[j].employeeAuthorityGroupItems;
//						  $scope.$digest();
//						  break;
//					  }
//				  }
//				  break;
//			  }
//		  }
//	  };
  });
