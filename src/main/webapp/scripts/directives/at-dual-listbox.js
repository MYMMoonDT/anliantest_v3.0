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
      restrict: 'E',
      //transclude: true,
      template: '<select multiple></select>',
      replace: true,
      scope: {
    	  groups: '=',
      },
      link: function postLink(scope, element, attrs) {
        element.bootstrapDualListbox({
        	moveOnSelect: false,
        });
        for (var i in scope.groups) {
  	    	if (scope.groups[i].isActive) {
  	    		element.append('<option selected>'+scope.groups[i].name+'</option>');
  	    	} else {
  	    		element.append('<option>'+scope.groups[i].name+'</option>');
  	    	}
  	    }
//        element.append('<option selected>zxc</option><option>asd</option>');
        element.bootstrapDualListbox('refresh');
      }
    };
  });
