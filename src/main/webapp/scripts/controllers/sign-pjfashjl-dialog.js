'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:SignPjfashjlDialogCtrl
 * @description
 * # SignPjfashjlDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('SignPjfashjlDialogCtrl', function ($scope, $rootScope, $modalInstance, data, dialogs) {
    $scope.data = data;

    if($scope.data.saveText == undefined) {
      $scope.data.saveText = '确定';
    }

    $scope.employee = $rootScope.employee;

    $scope.data.item = {
      reviewComment: '',
      createDate: new Date()
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
