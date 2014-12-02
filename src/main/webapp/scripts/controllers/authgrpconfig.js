'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:AuthGrpConfigCtrl
 * @description
 * # AuthgrpconfigCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('AuthGrpConfigCtrl', function ($scope, dialogs, AuthorityService) {
  	 var promise = AuthorityService.getAllAuthorityGroups();
     
  	 promise.then(function(data) {
    	 $scope.authorityGroups = data.data;
    	 $scope.selectedAuthGrp = data.data[0];

    	 
    	 $scope.$watch('selectedAuthGrp', function() {
    		 $scope.loadAuthItems();
				});
     });
  	 
  	 $scope.loadAuthItems = function() {
  		 var promise = AuthorityService.getAllAuthorityItems();
  		 promise.then(function(data) {
	  		 $scope.list = [];
	  		 $scope.items = data.data;
	  		 for (var i in $scope.items) {
	  			 var found = false;
	  			 for (var j in $scope.selectedAuthGrp.authorityItems) {
	  				 if ($scope.items[i].id == $scope.selectedAuthGrp.authorityItems[j].id) {
	  					 $scope.list.push({id: $scope.items[i].id, name: $scope.items[i].name, selected: true});
	  					 found = true;
	  					 break;
	  				 }
	  			 }
	  			 if (!found)
	  				 $scope.list.push({id: $scope.items[i].id, name: $scope.items[i].name, selected: false});
	  		 }
  		 });
  	 };
  	 
  	 $scope.save = function() {
  		 $scope.selectedAuthGrp.authorityItems = [];
  		 for (var i in $scope.list) {
  			 if ($scope.list[i].selected) {
  				 $scope.selectedAuthGrp.authorityItems.push({id: $scope.list[i].id});
  			 }
  		 }
  		 var promise = AuthorityService.updateAuthorityGroup($scope.selectedAuthGrp);
  		 promise.then(function(data) {
  			 var retGrp = data.data;
  			 for (var i in $scope.authorityGroups) {
  				 if ($scope.authorityGroups[i].id == retGrp.id) {
  					 $scope.authorityGroups[i] = retGrp;
  					 $scope.selectedAuthGrp = retGrp;
  					 break;
  				 }
  			 }
  		 });
  	 };
  	 
  	 $scope.showAddAuthGrpDialog = function() {
  		 var dialog = dialogs.create('template/at-add-authgrp-dialog.html', 'addAuthGrpDialogCtrl', 
           {
             
           }, 
           {
             size: 'md',
             keyboard: true,
             backdrop: 'static',
             windowClass: 'model-overlay',
           }
       );
       dialog.result.then(function(authGrpName) {
      	 var promise = AuthorityService.addAuthorityGroup(authGrpName);
      	 promise.then(function(data) {
      		 AuthorityService.getAllAuthorityGroups()
	      	 	.then(function(data) {
	      	 		$scope.authorityGroups = data.data;
	      	 		$scope.selectedAuthGrp = data.data[data.data.length-1];
	      	 	});
      	 });
       });
  	 };
  	 
  	 $scope.deleteAuthGrp = function() {
  		 var promise = AuthorityService.deleteAuthGrp($scope.selectedAuthGrp.id);
  		 promise.then(function(data){
  			 if (data.callStatus == 'SUCCEED') {
  				 for (var i in $scope.authorityGroups) {
  					 if ($scope.selectedAuthGrp.id == $scope.authorityGroups[i].id) {
  						 $scope.authorityGroups.splice(i,1);
  						 break;
  					 }
  				 }
  				 $scope.selectedAuthGrp = ($scope.authorityGroups != null ? $scope.authorityGroups[0] : null);
  			 } else {
  				 dialogs.create('template/at-alert-dialog.html', 'AlertCtrl',
  						 {text : data.errorCode},
  						 {
  							 size : 'sm',
  							 keyboard : true,
  							 backdrop : 'static',
  							 windowClass : 'model-overlay'
  				 });
  			 }
  		 });
  	 };
  })
  
  .controller('addAuthGrpDialogCtrl',function($scope, $modalInstance) {
 	 	$scope.cancel = function() {
 	 		$modalInstance.dismiss('Canceled');
		};
		
		$scope.save = function() {
		  $modalInstance.close($scope.authGrpName);
		};
  });
