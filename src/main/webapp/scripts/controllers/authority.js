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
	  $scope.selectedAuthGrp = {};
	  $("#authGrpSelect").hide();
	  $("#updateBtn").hide();
	  $("#configBtn").hide();
	  $("#authList").hide();
	  
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
//    			  levels: 1,
    			  onNodeSelected: function(event, node) {
    				  var hasAuth = false;
    				  for (var i in $scope.employees) {
    					  if (node.employeeId === $scope.employees[i].id) {
    						  $scope.curAuthEmployee = $scope.employees[i];
    						  $scope.authorityGroups = $scope.employees[i].employeeAuthorityGroups;
    						  $scope.$digest();
    						  for (var j in $scope.authorityGroups) {
    							  if ($scope.authorityGroups[j].isActive) {
    								  $scope.authorityItems = $scope.authorityGroups[j].employeeAuthorityGroupItems;
    								  $scope.selectedAuthGrp = $scope.authorityGroups[j];
    								  hasAuth = true;
    								  break;
    							  }
    						  }
    						  break;
    					  }
    				  }
    				  $scope.$digest();
    				  if (hasAuth) {
    					  $("#authGrpSelect").show();
    					  $("#updateBtn").show();
    					  $("#configBtn").show();
    					  $("#authList").show();
    				  } else {
    					  $("#authGrpSelect").hide();
    					  $("#updateBtn").hide();
    					  $("#configBtn").hide();
    					  $("#authList").hide();
    				  }
//    					  $scope.authorityItems = [];
//    				  $('.selectpicker').selectpicker();
    			  }
    	  };

    	  $('#tree').treeview({data: treeData});
    	  $('#tree').treeview(options);
//    	  $('treeWrap').scrollspy();
//    	  $("#tree").css("overflow", "auto");
      }, function (){
    	  
      });
      
      $scope.updateAuthority = function() {
    	  EmployeeService.updateEmployee($scope.curAuthEmployee);  
      }
      
      $scope.changeAuthrityGroup = function() {
    	  for (var i in $scope.authorityGroups) {
    		  if ($scope.authorityGroups[i] === $scope.selectedAuthGrp) {
    			  $scope.authorityGroups[i].isActive = true;
    			  $scope.authorityItems = $scope.authorityGroups[i].employeeAuthorityGroupItems;
    		  } else {
    			  $scope.authorityGroups[i].isActive = false;
    		  }
    	  }
//    	  $scope.$digest();
      }
  });
