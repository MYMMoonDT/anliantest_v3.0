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
      var dialog = dialogs.create('template/at-input-jcbg-dialog.html', 'inputJCBGDialogCtrl', 
      {
        project: $scope.project
      }, 
      {
        size: 'sg',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function(data) {

      }, function() {

      });
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

  .controller('inputJCBGDialogCtrl', function ($scope, $modalInstance, dialogs, data, ZYBWHYS, JCBG, JSJG, JGPJ) {
    var DEFAULT_ZYBWHYS_TD_HEIGHT = 135;
    var DEFAULT_TESTDATE_TD_HEIGHT = 110;
    var DEFAULT_SAMPLE_TD_HEIGHT = 55;

    $scope.data = data;

    $scope.canDownloadJSGCB = false;
    $scope.canDownloadJGYPDB = false;
    
    $scope.data.item = [];

    loadAllZYBWHYSItem();

    $scope.addWorkshopPosition = function() {
      var workshopPosition = {
        workshopPosition: '',
        list: []
      };
      $scope.data.item.push(workshopPosition);
    };

    $scope.deleteWorkshopPosition = function(workshopPosition) {
      var index = $scope.data.item.indexOf(workshopPosition);
      $scope.data.item.splice(index, 1);
    };

    $scope.addZYBWHYSItem = function(workshopPosition) {
      var zybwhysItem = {
        zybwhysItem: null,
        zybwhysItemDetailName: '',
        height: DEFAULT_ZYBWHYS_TD_HEIGHT,
        list: []
      };
      workshopPosition.list.push(zybwhysItem);
    };

    $scope.deleteZYBWHYSItem = function(workshopPosition, zybwhysItem) {
      var index = workshopPosition.list.indexOf(zybwhysItem);
      workshopPosition.list.splice(index, 1);
    };

    $scope.addTestDate = function(zybwhysItem) {
      var testDate = {
        testDate: new Date(),
        height: DEFAULT_TESTDATE_TD_HEIGHT,
        list: []
      };
      zybwhysItem.list.push(testDate);

      var height = 0;
      for(var i = 0; i < zybwhysItem.list.length; i++) {
        height += zybwhysItem.list[i].height;
      }
      zybwhysItem.height = height > zybwhysItem.height ? height : zybwhysItem.height;
    };

    $scope.deleteTestDate = function(zybwhysItem, testDate){
      var index = zybwhysItem.list.indexOf(testDate);
      zybwhysItem.list.splice(index, 1);

      var height = 0;
      for(var i = 0; i < zybwhysItem.list.length; i++) {
        height += zybwhysItem.list[i].height;
      }
      zybwhysItem.height = height;
    };

    $scope.addSample = function(zybwhysItem, testDate) {
      var sample = {
        sampleNum: '',
        result: '',
        touchTime: '',
        collectTime: '',
        height: DEFAULT_SAMPLE_TD_HEIGHT
      };
      testDate.list.push(sample);

      var height = 0;
      for(var i = 0; i < testDate.list.length; i++) {
        height += testDate.list[i].height;
      }
      testDate.height = height > testDate.height ? height : testDate.height;

      height = 0;
      for(var i = 0; i < zybwhysItem.list.length; i++) {
        height += zybwhysItem.list[i].height;
      }
      zybwhysItem.height = height > zybwhysItem.height ? height : zybwhysItem.height;
    };

    $scope.deleteSample = function(zybwhysItem, testDate, sample) {
      var index = testDate.list.indexOf(sample);
      testDate.list.splice(index, 1);

      var height = 0;
      for(var i = 0; i < testDate.list.length; i++) {
        height += testDate.list[i].height;
      }
      testDate.height = height;

      height = 0;
      for(var i = 0; i < zybwhysItem.list.length; i++) {
        height += zybwhysItem.list[i].height;
      }
      zybwhysItem.height = height;
    };

    $scope.submit = function() {
      var canSubmit = true;
      for(var i = 0; i < $scope.data.item.length; i++) {
        if($scope.data.item[i].workshopPosition == undefined || 
           $scope.data.item[i].workshopPosition == null || 
           $scope.data.item[i].workshopPosition == '') {
          canSubmit = false;
          break;
        }
        for(var j = 0; j < $scope.data.item[i].list.length; j++) {
          if($scope.data.item[i].list[j].selected == undefined ||
             $scope.data.item[i].list[j].selected == null) {
            canSubmit = false;
            break;
          }
          for(var k = 0; k < $scope.data.item[i].list[j].list.length; k++) {
            if($scope.data.item[i].list[j].list[k].testDate == undefined ||
               $scope.data.item[i].list[j].list[k].testDate == null ||
               $scope.data.item[i].list[j].list[k].testDate == '') {
              canSubmit = false;
              break;
            }
            for(var p = 0; p < $scope.data.item[i].list[j].list[k].list.length; p++) {
              if($scope.data.item[i].list[j].list[k].list[p].sampleNum == undefined ||
                 $scope.data.item[i].list[j].list[k].list[p].sampleNum == null ||
                 $scope.data.item[i].list[j].list[k].list[p].sampleNum == '' || 
                 $scope.data.item[i].list[j].list[k].list[p].result == undefined ||
                 $scope.data.item[i].list[j].list[k].list[p].result == null ||
                 $scope.data.item[i].list[j].list[k].list[p].result == '' ||
                 $scope.data.item[i].list[j].list[k].list[p].touchTime == undefined ||
                 $scope.data.item[i].list[j].list[k].list[p].touchTime == null ||
                 $scope.data.item[i].list[j].list[k].list[p].touchTime == '' ||
                 $scope.data.item[i].list[j].list[k].list[p].collectTime == undefined ||
                 $scope.data.item[i].list[j].list[k].list[p].collectTime == null ||
                 $scope.data.item[i].list[j].list[k].list[p].collectTime == '') {
                canSubmit = false;
                break;
              }
            }
            if(!canSubmit)
              break;
          }
          if(!canSubmit)
            break;
        }
        if(!canSubmit)
          break;
      }

      if(!canSubmit) {
        dialogs.create('template/at-alert-dialog.html', 'AlertCtrl', 
        {
          text: '数据输入不完整'
        }, 
        {
          size: 'sm',
          keyboard: true,
          backdrop: 'static',
          windowClass: 'model-overlay'
        });
        return;
      }
        

      var jcbg = new JCBG();

      var list = [];
      for(var i = 0; i < $scope.data.item.length; i++) {
        for(var j = 0; j < $scope.data.item[i].list.length; j++) {
          var item = {
            workshopPosition: $scope.data.item[i].workshopPosition,
            zybwhysItem: $scope.data.item[i].list[j].selected.chineseName,
            zybwhysItemDetailName: $scope.data.item[i].list[j].zybwhysItemDetailName,
            testDate: [],
            sampleNum: [],
            result: [],
            touchTime: [],
            collectTime: []
          };

          for(var k = 0; k < $scope.data.item[i].list[j].list.length; k++) {
            item.testDate.push($scope.data.item[i].list[j].list[k].testDate);
            var sampleNum = [];
            var result = [];
            var touchTime = [];
            var collectTime = [];
            for(var p = 0; p < $scope.data.item[i].list[j].list[k].list.length; p++) {
              sampleNum.push($scope.data.item[i].list[j].list[k].list[p].sampleNum);
              result.push($scope.data.item[i].list[j].list[k].list[p].result);
              touchTime.push($scope.data.item[i].list[j].list[k].list[p].touchTime);
              collectTime.push($scope.data.item[i].list[j].list[k].list[p].collectTime);
            }
            item.sampleNum.push(sampleNum);
            item.result.push(result);
            item.touchTime.push(touchTime);
            item.collectTime.push(collectTime);
          }
          list.push(item);
        }
      }

      angular.extend(jcbg, {list:list});

      jcbg.$input({projectId: $scope.data.project.id}, function(data){
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
    };
    
    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };

    $scope.downloadJSGCB = function() {
      window.location.href = "api/jsjg/download?projectId="+$scope.data.project.id;
    };

    $scope.downloadJGYPDB = function() {
      window.location.href = "api/jgpj/download?projectId="+$scope.data.project.id;
    };

    function loadAllZYBWHYSItem() {
      var zybwhys = new ZYBWHYS();
      zybwhys.$query(function(data){
        $scope.zybwhysList = data.data;
      });
    }
  });
