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
      link: function postLink(scope, element, attrs, ngModel) {
        var stepMap = {
          'STEP1': {
            id: 1,
            name: '1.项目录入'
          },
          'STEP2': {
            id: 2,
            name: '2.项目下达'
          },
          'STEP3': {
            id: 3,
            name: '3.项目前期准备'
          },
          'STEP4': {
            id: 4,
            name: '4.项目检测环节'
          },
          'STEP5': {
            id: 5,
            name: '5.项目实验环节'
          },
          'STEP6': {
            id: 6,
            name: '6.项目数据处理'
          }
        };

        ngModel.$render = function () {
          var val = ngModel.$viewValue || null;
          if(val != null) {
            var promise = val.$promise;
            promise.then(function(){
              var stepId = stepMap[val.step].id;
              var $progressBar = element.progressStep();
              for(var step in stepMap) {
                $progressBar.addStep(stepMap[step].name);
                $progressBar.getStep(stepMap[step].id - 1).onClick = function() {
                  
                };
              }
              element.refreshLayout();  
              element.setCurrentStep(stepId-1);
            });
          }
        };
      }
    };
  });
