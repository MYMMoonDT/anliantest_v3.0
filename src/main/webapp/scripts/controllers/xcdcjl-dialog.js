'use strict';

/**
 * @ngdoc function
 * @name anliantestApp.controller:XcdcjlDialogCtrl
 * @description
 * # XcdcjlDialogCtrl
 * Controller of the anliantestApp
 */
angular.module('anliantestApp')
  .controller('XcdcjlDialogCtrl', function ($scope, $modalInstance, dialogs, data, Files) {
    $scope.data = data;

    loadResourceList();

    $scope.data.item = {
      tableNum: 'ALJC/JL32-13',
      revisionStatus: '1/0',

      supportResourceContent: '',
      processFlowContent: '',
      sourceListContent: '',

      layoutFile: null,

      investigateEmployee: '',
      attendEmployee: '',
      createDate: new Date()
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $scope.data.item.layoutFile = {
        id: $scope.data.item.layoutFile.id
      };
      $modalInstance.close($scope.data);
    };

    function loadResourceList() {
      var files = new Files();
      files.$list({projectId : data.project.id}, function(data){
        $scope.resourceList = data.data;
      });
    }
  });
