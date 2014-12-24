'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:PjfashjlDialogCtrl
 * @description
 * # PjfashjlDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('PjfashjlDialogCtrl', function ($scope, $modalInstance, data, dialogs) {
    $scope.data = data;

    $scope.data.item = {
      tableNum: 'ALJC/JL32-05',
      revisionStatus: '1/0',

      reviewHost: '',
      reviewAttendant: '',
      reviewWriter: '',
      createDate: new Date(),

      reviewRecord: '',
      reviewEmployee: '',
      reviewDate: new Date(),

      modifyResult: '',
      modifyEmployee: '',
      modifyDate: new Date(),
    };

    /*
    $scope.selectReviewEmployee = function() {
      var dialog = dialogs.create('template/at-select-employee-dialog.html', 'SelectEmployeeDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        $scope.data.item.reviewEmployee = data;
      }, function () {
        
      });
    };

    $scope.selectModifyEmployee = function() {
      var dialog = dialogs.create('template/at-select-employee-dialog.html', 'SelectEmployeeDialogCtrl', {}, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function (data) {
        $scope.data.item.modifyEmployee = data;
      }, function () {
        
      });
    }; 
    */

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
