'use strict';

/**
 * @ngdoc directive
 * @name anliantestApp.directive:atDatepicker
 * @description
 * # atDatepicker
 */
angular.module('anliantestApp')
  .directive('atDatepicker', function () {
    return {
      restrict: 'A',
      scope: {
        date: '='
      },
      link: function postLink(scope, element, attrs) {
        element.datepicker({
          format: "yyyy-mm-dd",
          language: "zh-CN"
        })
        .on('changeDate',function(event){
          scope.date = element.datepicker('getDate').getTime();
        });
        element.datepicker('setDate', new Date(scope.date));
      }
    };
  });
