'use strict';

angular.module('anliantestApp')
  .filter('projectStatus', function () {
    return function (input) {
      var ret = '';

      switch (input) {
        case 'CREATE_HTPSJL':
          ret = '创建合同评审记录';
          break;
        case 'SIGN_HTPSJL':
          ret = '合同评审记录签字';
          break;
        case 'CREATE_GZRWD':
          ret = '创建工作任务单';
          break;
        case 'APPOINT_XMFZR':
          ret = '指定项目负责人';
          break;
        case 'CREATE_KHZLDJD':
          ret = '创建客户资料登记单';
          break;
        case 'CREATE_XCDCJL':
          ret = '创建现场调查记录';
          break;
        case 'UPLOAD_PJFA':
          ret = '上传评价方案';
          break;
        case 'CREATE_PJFASHJL':
          ret = '创建评价方案审核记录';
          break;
        case 'SIGN_PJFASHJL':
          ret = '评价方案审核记录签字';
          break;
        case 'CREATE_JCTZD':
          ret = '创建检测通知单';
          break;
      }

      return ret;
    }
  });