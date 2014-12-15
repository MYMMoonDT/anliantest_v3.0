'use strict';

angular.module('anliantestApp')
  .filter('departmentType', function () {
    return function (input) {
      var ret = '';

      switch (input) {
        case 'MARKET':
          ret = '市场部';
          break;
        case 'EVALUATION':
          ret = '评价部';
          break;
        case 'DETECTION':
          ret = '检测部';
          break;
        case 'ADMIN':
          ret = '行政部';
          break;
        case 'QUALITY':
          ret = '质控部';
          break;
        case 'MANAGER':
          ret = '总经理';
          break;
        case 'TEACHNICAL_DIRECTOR':
          ret = '技术负责人';
          break;
        case 'QUALITY_DIRECTOR':
          ret = '质量负责人';
          break;
      }

      return ret;
    }
  });