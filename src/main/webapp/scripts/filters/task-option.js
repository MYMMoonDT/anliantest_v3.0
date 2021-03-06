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
        case 'UPLOAD_PJFA':
          ret = '上传';
          break;
        case 'CREATE_PJFASHJL':
          ret = '创建';
          break;
        case 'SIGN_PJFASHJL':
          ret = '签字';
          break;
        case 'CREATE_JCTZD':
          ret = '创建';
          break;
        case 'CONFIRM_CYFA':
          ret = '确认';
          break;
        case 'CREATE_SYSYJL':
          ret = '创建';
          break;
        case 'CONFIRM_SYSYJL':
          ret = '确认';
          break;
        case 'INPUT_JCBG':
          ret = '输入';
          break;
        default:
          ret = '创建';
      }

      return ret;
    }
  });