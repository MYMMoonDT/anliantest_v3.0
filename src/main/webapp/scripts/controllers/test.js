'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:TestCtrl
 * @description
 * # TestCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('TestCtrl', function ($scope) {
    $scope.list = [
                   {name:'apple', selected:true},
                   {name:'cake', selected:false},
                   {name:'sausage', selected:false},
                   ];
    $scope.reset = function() {
    	$scope.list = [
                       {name:'apple', selected:true},
                       {name:'cake', selected:false},
                       {name:'sausage', selected:false},
                       ];

    };
  });
