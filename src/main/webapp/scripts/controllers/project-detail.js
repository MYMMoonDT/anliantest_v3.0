'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:ProjectDetailCtrl
 * @description
 * # ProjectDetailCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('ProjectDetailCtrl', function ($scope, $routeParams, Project) {
    $scope.projectId = $routeParams.id;

    refreshData();

    function refreshData() {
      $scope.project = Project.get({projectId: $scope.projectId});
    }
    
  });
