'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:XcdcjlDialogCtrl
 * @description
 * # XcdcjlDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('XcdcjlDialogCtrl', function ($scope, $modalInstance, dialogs, data) {
    $scope.data = data;

    $scope.data.item = {
      createDate: new Date()
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
