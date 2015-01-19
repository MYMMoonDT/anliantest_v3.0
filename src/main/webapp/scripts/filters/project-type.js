'use strict';

angular.module('anliantestApp')
  .filter('projectType', function () {
    return function (input) {
      var ret = '';

      switch (input) {
        case 'JSXMZYBWHKZXGPJ':
          ret = '建设项目职业病危害控制效果评价';
          break;
        case 'JSXMZYBWHYPJ':
          ret = '建设项目职业病危害预评价';
          break;
        case 'JSXMZYBWHXZPJ':
          ret = '建设项目职业病危害现状评价';
          break;
        case 'GGCSWSJCYPJ':
          ret = '公共场所卫生检测与评价';
          break;
        case 'GZCSZYBWHYSJCYPJ':
          ret = '工作场所职业病危害因素检测与评价';
          break;
      }

      return ret;
    }
  });