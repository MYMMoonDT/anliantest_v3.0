'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:AlertCtrl
 * @description
 * # AlertCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('AlertCtrl', function ($scope, $modalInstance, data) {
    $scope.data = data;

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
  });
