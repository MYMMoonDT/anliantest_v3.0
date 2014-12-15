'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:GzrwdDialogCtrl
 * @description
 * # GzrwdDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('GzrwdDialogCtrl', function ($scope, $modalInstance, data) {
    $scope.data = data;

    $scope.data.item = {
      tableNum: 'ALJC/JL32-01',
      revisionStatus: '1/0',
      issueDate: new Date(),
      items: [
        {
          groupName: '评价组',
          workContent: '',
          workTimeLimit: '',
        }, 
        {
          groupName: '检测组',
          workContent: '',
          workTimeLimit: '',
        },
        {
          groupName: '质控组',
          workContent: '',
          workTimeLimit: '',
        },
        {
          groupName: '其他',
          workContent: '',
          workTimeLimit: '',
        }
      ]
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
