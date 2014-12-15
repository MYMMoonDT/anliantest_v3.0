'use strict';

/**
 * @ngdoc service
 * @name anliantestApp.progress
 * @description
 * # progress
 * Service in the anliantestApp.
 */
angular.module('anliantestApp')
  .service('ProgressService', function ProgressService() {
    var stepMap = {
      'STEP1': {
        id: 1,
        name: '1.项目录入'
      },
      'STEP2': {
        id: 2,
        name: '2.项目下达'
      },
      'STEP3': {
        id: 3,
        name: '3.项目前期准备'
      },
      'STEP4': {
        id: 4,
        name: '4.项目检测环节'
      },
      'STEP5': {
        id: 5,
        name: '5.项目实验环节'
      },
      'STEP6': {
        id: 6,
        name: '6.项目数据处理'
      }
    };

    this.getStepMap = function() {
      return stepMap;
    };
  });
