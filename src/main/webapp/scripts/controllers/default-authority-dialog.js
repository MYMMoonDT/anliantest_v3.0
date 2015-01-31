'use strict';

angular.module('anliantestApp')
  .controller('DefaultAuthorityDialogCtrl', function($scope, $modalInstance, dialogs, data, Authority) {
    $scope.data = data;

    loadAllAuthorityGroup();

    $scope.addAuthorityGroup = function() {
      var dialog = dialogs.create('template/at-add-default-authority-dialog.html', 'addDefaultAuthorityDialogCtrl', {}, {
        size: 'sm',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
      dialog.result.then(function(data) {
        var authority = new Authority();

        authority.$addGroup({
          groupName: data.item.groupName
        }, function() {
          loadAllAuthorityGroup();
        });
      }, function() {

      });
    };

    $scope.editAuthorityGroup = function(group) {
      var dialog = dialogs.create('template/at-edit-default-authority-dialog.html', 'editDefaultAuthorityDialogCtrl', {
        item: group
      }, {
        size: 'md',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
      dialog.result.then(function(data) {
        var authority = new Authority();
        angular.extend(authority, data.item);
        authority.$updateGroup(function(){
          loadAllAuthorityGroup();
        });
      }, function() {

      });
    };

    $scope.deleteAuthorityGroup = function(group) {
      var dialog = dialogs.create('template/at-confirm-dialog.html', 'ConfirmCtrl', {
        text: '是否删除该权限组?',
        type: 'DELETE'
      }, {
        size: 'sm',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      dialog.result.then(function() {
        var authority = new Authority();

        authority.$deleteGroup({
          id: group.id
        }, function() {
          loadAllAuthorityGroup();
        });
      });
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('Canceled');
    };
    $scope.save = function() {
      $modalInstance.close($scope.data);
    };

    function loadAllAuthorityGroup() {
      var authority = new Authority();
      authority.$groupAll(function(data) {
        $scope.authorityGroups = data.data;
      });
    }
  })

.controller('addDefaultAuthorityDialogCtrl', function($scope, $modalInstance, data) {
  $scope.data = data;

  $scope.data.item = {
    groupName: ''
  };

  $scope.cancel = function() {
    $modalInstance.dismiss('Canceled');
  };
  $scope.save = function() {
    $modalInstance.close($scope.data);
  };
})

.controller('editDefaultAuthorityDialogCtrl', function($scope, $modalInstance, data, Authority) {
  $scope.data = data;

  $scope.data.configList = [];

  loadAllAuthorityItem();

  $scope.cancel = function() {
    $modalInstance.dismiss('Canceled');
  };
  $scope.save = function() {
    $scope.data.item.authorityItems = [];
    for (var i = 0; i < $scope.data.configList.length; i++) {
      if ($scope.data.configList[i].selected) {
        $scope.data.item.authorityItems.push({id: $scope.data.configList[i].id});
      }
    }
    $modalInstance.close($scope.data);
  };

  function loadAllAuthorityItem() {
    var authority = new Authority();
    authority.$itemAll(function(data) {

      for(var i = 0; i < data.data.length; i++) {
        var found = false;
        for(var j = 0; j < $scope.data.item.authorityItems.length; j++) {
          if($scope.data.item.authorityItems[j].id == data.data[i].id) {
            $scope.data.configList.push({id: data.data[i].id, name: data.data[i].name, selected: true});
            found = true;
            break;
          }
        }
        if(!found)
          $scope.data.configList.push({id: data.data[i].id, name: data.data[i].name, selected: false});
      }
    });
  }
});