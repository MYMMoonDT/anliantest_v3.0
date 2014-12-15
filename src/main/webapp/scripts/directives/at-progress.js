'use strict';

/**
 * @ngdoc directive
 * @name anliantestApp.directive:atProgress
 * @description
 * # atProgress
 */
angular.module('anliantestApp')
  .directive('atProgress', function () {
    return {
      restrict: 'A',
      require: 'ngModel',
      scope: {},
      controller: function($scope, ProgressService, dialogs) {
        $scope.showStepDetailDialog = function(project, stepId) {
          var dialog = dialogs.create('template/at-progress-dialog.html', 'ProgressDialogCtrl', {
            item: project,
            stepId: stepId
          }, 
          {
            size: 'md',
            keyboard: true,
            backdrop: true,
            windowClass: 'model-overlay'
          });
        };

        $scope.getStepMap = function() {
          return ProgressService.getStepMap();
        };
      },
      link: function postLink(scope, element, attrs, ngModel) {
        var stepMap = scope.getStepMap();

        ngModel.$render = function () {
          var val = ngModel.$viewValue || null;
          if(val != null) {
            var promise = val.$promise;
            promise.then(function(){
              var stepId = stepMap[val.step].id;
              var $progressBar = element.progressStep();
              for(var step in stepMap) {
                $progressBar.addStep(stepMap[step].name);

                (function(){
                  var stepTemp = step;
                  $progressBar.getStep(stepMap[stepTemp].id - 1).onClick = function() {
                    if(stepMap[stepTemp].id <= stepId)
                      scope.showStepDetailDialog(val, stepMap[stepTemp].id);
                  };
                })();
                
              }
              element.refreshLayout();  
              element.setCurrentStep(stepId-1);
            });
          }
        };
      }
    };
  });