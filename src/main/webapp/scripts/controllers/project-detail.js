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

  .controller('uploadJCBGDialogCtrl', function ($scope, $modalInstance, $upload, dialogs, data, JSJG, JGPJ) {
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
    $scope.upload = function() {
      if($scope.uploadFile != undefined && $scope.uploadFile != null) {
        $scope.upload = $upload.upload({
          url: 'api/jcbg/upload',
          data: {projectId: $scope.data.project.id},
          file: $scope.uploadFile
        }).success(function(data, status, headers, config) {
          if(data.callStatus == 'FAILED') {
            var dialog = dialogs.create('template/at-alert-dialog.html', 'AlertCtrl', 
            {
              text: data.errorMsg
            }, 
            {
              size: 'sm',
              keyboard: true,
              backdrop: 'static',
              windowClass: 'model-overlay'
            });
          }else if(data.callStatus == 'SUCCEED') {
            $scope.canDownloadJSGCB = true;
            $scope.canDownloadJGYPDB = true;
          }
        });
      }
    };

    $scope.downloadJSGCB = function() {
      window.location.href = "api/jsjg/download?projectId="+$scope.data.project.id;
    };

    $scope.downloadJGYPDB = function() {
      window.location.href = "api/jgpj/download?projectId="+$scope.data.project.id;
    };
  })

  .controller('inputJCBGDialogCtrl', function ($scope, $modalInstance, data) {

  });
