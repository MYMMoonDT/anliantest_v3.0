'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:FilesDialogCtrl
 * @description
 * # FilesDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('FilesDialogCtrl', function ($scope, $modalInstance, dialogs, data) {
    $scope.data = data;
    $scope.data.item = {
      groupName: '',
      comment: '',
      uploadDate: null,
      project: {
        id: $scope.data.project.id
      },
      items: []
    };

    $scope.addFile = function() {
      var file = {
        fileName: '',
        file: null
      };
      $scope.data.item.items.push(file);
    };

    $scope.deleteFile = function(file) {
      var index = $scope.data.item.items.indexOf(file);
      $scope.data.item.items.splice(index, 1);
    };

    $scope.onFileSelect = function($files, file) {
      if($files.length > 0) {
        file.file = $files[0];
        file.fileName = file.file.name;
      }
    }

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };
  });
