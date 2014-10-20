'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:HtpsjlDialogCtrl
 * @description
 * # HtpsjlDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('HtpsjlDialogCtrl', function ($scope, $modalInstance, data) {
    $scope.data = data;

    $scope.data.item = {
      createDate: new Date(),
      items: [
        {
          department: {
            id: '2',
            name: '评价部'
          },
          reviewContent: '',
          reviewComment: '',
          createDate: new Date(),
        },
        {
          department: {
            id: '3',
            name: '检测部'
          },
          reviewContent: '',
          reviewComment: '',
          createDate: new Date(),
        },
        {
          department: {
            id: '4',
            name: '行政部'
          },
          reviewContent: '',
          reviewComment: '',
          createDate: new Date(),
        },
        {
          department: {
            id: '5',
            name: '质控部'
          },
          reviewContent: '',
          reviewComment: '',
          createDate: new Date(),
        },
        {
          department: {
            id: '6',
            name: '总经理'
          },
          reviewContent: '',
          reviewComment: '',
          createDate: new Date(),
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
