'use strict';

angular.module('anliantestApp')
  .filter('projectStep', function () {
    return function (input) {
      var ret = '';

      switch (input) {
        case 'STEP1':
          ret = '1.项目录入';
          break;
        case 'STEP2':
          ret = '2.项目下达';
          break;
        case 'STEP3':
          ret = '3.项目前期准备';
          break;
      }

      return ret;
    }
  });