'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:ProjectDetailCtrl
 * @description
 * # ProjectDetailCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('ProjectDetailCtrl', function ($scope, $routeParams, dialogs, Project) {
    $scope.projectId = $routeParams.id;

    refreshData();

    function refreshData() {
      $scope.project = Project.get({projectId: $scope.projectId}, function(data){
        $scope.step = data.step;
      });
    }

    $scope.showInputJCBGDialog = function() {

    };

    $scope.showUploadJCBGDialog = function() {
      var dialog = dialogs.create('template/at-upload-jcbg-dialog.html', 'uploadJCBGDialogCtrl', 
      {
        project: $scope.project
      }, 
      {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function(data) {

      }, function() {

      });
    };
    
  })

  .controller('uploadJCBGDialogCtrl', function ($scope, $modalInstance, $upload, data) {
    $scope.data = data;

    $scope.canDownloadJSGCB = false;
    $scope.canDownloadJGYPDB = false;

    $scope.onFileSelect = function($files) {
      if($files.length > 0) {
        $scope.uploadFile = $files[0];
      }
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      console.log($scope.data.project);
      if($scope.uploadFile != undefined && $scope.uploadFile != null) {
        $scope.upload = $upload.upload({
          url: 'api/jcbg/upload',
          data: {projectId: $scope.data.project.id},
          file: $scope.uploadFile
        }).success(function(data, status, headers, config) {
          
        });
      }
    };
  })

  .controller('inputJCBGDialogCtrl', function ($scope, $modalInstance, data) {

  });
