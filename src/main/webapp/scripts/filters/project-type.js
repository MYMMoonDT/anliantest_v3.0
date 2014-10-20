'use strict';

angular.module('anliantestApp')
  .filter('projectType', function () {
    return function (input) {
      var ret = '';

      switch (input) {
        case 'JSXMZYBWHKZXGPJ':
          ret = '建设项目职业病危害控制效果评价';
          break;
      }

      return ret;
    }
  });