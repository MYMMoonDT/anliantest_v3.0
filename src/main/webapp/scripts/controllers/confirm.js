'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:ConfirmCtrl
 * @description
 * # ConfirmCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('ConfirmCtrl', function ($scope, $modalInstance, data) {
    $scope.data = data;

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close();
    };
  });
