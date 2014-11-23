'use strict';

angular.module('angularToggleDirectives', [])
    .directive('toggle', function() {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                action: '&',
                model: '=ngModel',
                isDisabled: '='
            },
            template: '<input type="checkbox">',
            link: function(scope, element) {
                element.bootstrapSwitch(); // call bootstrapSwitch once the directive is compiled

                scope.$watch('model', function(newVal) {
                    element.bootstrapSwitch('state', newVal, true);
                });

                scope.$watch('isDisabled', function(newVal) {
                	if (newVal == null)
                		newVal = false;
                    element.bootstrapSwitch('disabled', newVal);
                });

                element.on('switchChange.bootstrapSwitch', function() {
                    scope.$apply(function(){
                        scope.model = !scope.model;
                    });
                    scope.$apply(function(){
                        scope.action();
                    });
                });
            }
        };
    });
