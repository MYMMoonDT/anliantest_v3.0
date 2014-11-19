'use strict';

/**
 * @ngdoc directive
 * @name anliantestApp.directive:atDualListbox
 * @description
 * # atDualListbox
 */
angular.module('anliantestApp')
  .directive('atDualListbox', function () {
    return {
      template: '<div>TEST</div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        element.text('this is the atDualListbox directive');
      }
    };
  });
