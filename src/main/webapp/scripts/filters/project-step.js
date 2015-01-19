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
        case 'STEP4':
          ret = '4.项目检测环节';
          break;
        case 'STEP5':
          ret = '5.项目实验环节';
          break;
        case 'STEP6':
          ret = '6.项目数据处理';
          break;
      }

      return ret;
    }
  });