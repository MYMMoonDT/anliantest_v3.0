'use strict';

angular.module('anliantestApp')
	.directive('atMultipleListbox', function () {
		return {
			restrict: 'E',
			templateUrl: 'template/at-multiple-listbox.html',
			replace: true,
			scope: {
				list: '=ngModel',
				lockedItems: '=',
				selectedTitle: '@',
				unselectedTitle: '@',
			},
			link: function (scope, element, attrs) {
				scope.activeItems = new Set();
				
				scope.itemClicked = function setActive(item) {
					if (scope.isLocked(item))
						return;
					if (scope.activeItems.has(item)) {
						scope.activeItems.delete(item);
					} else {
						scope.activeItems.forEach(function (value) {
							if (value.selected !== item.selected) {
								scope.activeItems.delete(value);
							}
						})
						scope.activeItems.add(item);
					}
				};
				
				scope.isActive = function (item) {
					return scope.activeItems.has(item);
				};
				
				scope.rightClicked = function setUnselected() {
					scope.activeItems.forEach(function (value) {
						value.selected = false;
					});
					scope.activeItems.clear();
				};
				
				scope.leftClicked = function setUnselected() {
					scope.activeItems.forEach(function (value) {
							value.selected = true;
					});
					scope.activeItems.clear();
				};
				
				scope.isLocked = function (item) {
					for (var i in scope.lockedItems) {
						if (scope.lockedItems[i].id === item.id)
							return true;
					}
					return false;
				}
				
				scope.$watch(attrs.ngModel, function() {
					scope.activeItems.clear();
				});
			}
		};
	});