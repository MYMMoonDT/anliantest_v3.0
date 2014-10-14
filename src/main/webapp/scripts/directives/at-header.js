'use strict';

/**
 * @ngdoc directive
 * @name anliantestApp.directive:atHeader
 * @description
 * # atHeader
 */
angular.module('anliantestApp')
  .directive('atHeader', function () {
    return {
      templateUrl: 'template/at-header.html',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        
      }
    };
  });
