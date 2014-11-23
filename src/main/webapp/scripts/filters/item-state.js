'use strict';

/**
 * @ngdoc filter
 * @name anliantestApp.filter:itemState
 * @function
 * @description
 * # itemState
 * Filter in the anliantestApp.
 */
angular.module('anliantestApp')
  .filter('itemState', function () {
    return function (input, state) {
      return input.selected == state;
    };
  });
