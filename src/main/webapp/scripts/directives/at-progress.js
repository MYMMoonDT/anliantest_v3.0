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
      scope: {
        step: '='
      },
      controller: function($scope, $attrs) {
      },
      link: function postLink(scope, element, attrs) {
        
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
          }
        };

        scope.$watch(attrs.step, function(data){
          if(data != undefined) {
            var step = stepMap[scope.step].id;
            element.progressStep();
            for(var i = 1; i <= step; i++) {
              var stepEnum = 'STEP' + i;
              element.addStep(stepMap[stepEnum].name); 
            }
            element.refreshLayout();  
            element.setCurrentStep(step-1);
          }
        });
      }
    };
  });
