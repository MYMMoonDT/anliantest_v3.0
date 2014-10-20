'use strict';

angular.module('anliantestApp')
  .filter('taskOption', function () {
    return function (input) {
      var ret = '';

      switch (input) {
        case 'CREATE_HTPSJL':
          ret = '创建';
          break;
        case 'SIGN_HTPSJL':
          ret = '签字';
          break;
        case 'CREATE_GZRWD':
          ret = '创建';
          break;
        case 'APPOINT_XMFZR':
          ret = '指定';
          break;
        case 'CREATE_KHZLDJD':
          ret = '创建';
          break;
        case 'CREATE_XCDCJL':
          ret = '创建';
          break;
      }

      return ret;
    }
  });