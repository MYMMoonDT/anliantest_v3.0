'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:ProjectDetailCtrl
 * @description
 * # ProjectDetailCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('ProjectDetailCtrl', function ($scope, $routeParams, $upload, dialogs, Project, Files) {
    $scope.projectId = $routeParams.id;

    $scope.fileCurrPageNum = 1;
    $scope.fileTotalItemNum = 0;

    refreshProjectData();
    refreshProjectFileData();

    function refreshProjectData() {
      $scope.project = Project.get({projectId: $scope.projectId}, function(data){
        $scope.step = data.step;
      });
    }

    function refreshProjectFileData(){
      Files.query({projectId: $scope.projectId, currPageNum: $scope.fileCurrPageNum}, function(data){
        if(data != null) {
          $scope.projectFileGroupList = data.data;
          $scope.fileCurrPageNum = data.currPageNum;
          $scope.fileTotalItemNum = data.totalItemNum;
        }
      });
    }

    $scope.filePageChanged = function(){
      refreshProjectFileData();
    };

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

    $scope.showAddFilesDialog = function() {
      var dialog = dialogs.create('template/at-files-dialog.html', 'FilesDialogCtrl', 
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
        
        var files = [];
        for(var i = 0; i < data.item.items.length; i++) {
          files.push(data.item.items[i].file);
          delete data.item.items[i].file;
        }

        $upload.upload({
          url: 'api/files',
          method: 'POST',
          data: data.item,
          file: files
        }).success(function(data, status, headers, config) {
          refreshProjectFileData();
        });
        
        /*
        var files = [];
        for(var i = 0; i < data.item.items.length; i++) {
          files.push(data.item.items[i].file);
          delete data.item.items[i].file;
        }
        var files = new Files();
        
        angular.extend(files, data.item);

        files.$save(function(){
          refreshProjectFileData();
        });
        */

      }, function() {

      });
    };
    
    $scope.editFiles = function(fileGroup){

    };

    $scope.deleteFiles = function(fileGroup) {
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', 
      {
        text: '确定要删除该资料?',
        type: 'DELETE'
      }, 
      {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function () {
        var _files = new Files();

        _files.$delete({fileGroupId:fileGroup.id}, function(){
          refreshProjectFileData();
        });

      }, function () {
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
        $upload.upload({
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
