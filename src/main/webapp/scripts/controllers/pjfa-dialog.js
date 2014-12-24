'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:PjfaDialogCtrl
 * @description
 * # PjfaDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('PjfaDialogCtrl', function ($scope, $modalInstance, data, Files) {
    $scope.data = data;

    loadResourceList();

    $scope.data.item = {
      pjfaFile: null
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $scope.data.item.pjfaFile = {
        id: $scope.data.item.pjfaFile.id
      };
      $modalInstance.close($scope.data);
    };

    function loadResourceList() {
      var files = new Files();
      files.$list({projectId : data.project.id}, function(data){
        $scope.resourceList = data.data;
      });
    }
  });
