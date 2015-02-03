'use strict';

/**
 * @ngdoc service
 * @name anliantestApp.AuthorityService
 * @description
 * # AuthorityService
 * Service in the anliantestApp.
 */
angular.module('anliantestApp')
  .service('AuthorityService', function AuthorityService($http, $q) {
    var authorityMap = {
      'CREATE_HTPSJL' : {
        authorityType: 'mHTPSJL'
      },
      'SIGN_HTPSJL': {
        authorityType: 'mHTPSJL'
      },
      'CREATE_GZRWD': {
        authorityType: 'mGZRWD'
      },
      'APPOINT_XMFZR': {
        authorityType: 'mProject'
      },
      'CREATE_KHZLDJD': {
        authorityType: 'mKHZLDJD'
      },
      'CREATE_XCDCJL': {
        authorityType: 'mXCDCJL'
      },
      'UPLOAD_PJFA': {
        authorityType: 'mPJFA'
      },
      'CREATE_PJFASHJL': {
        authorityType: 'mPJFASHJL'
      },
      'SIGN_PJFASHJL': {
        authorityType: 'mPJFASHJL'
      },
      'CREATE_JCTZD': {
        authorityType: 'mJCTZD'
      },
      'CONFIRM_CYFA': {
        authorityType: 'mCYFA'
      },
      'CREATE_SYSYJL': {
        authorityType: 'mSYSYJL'
      },
      'CONFIRM_SYSYJL': {
        authorityType: 'mSYSYJL'
      },
      'INPUT_JCBG': {
        authorityType: 'mJCBG'
      }
    };

    this.getAuthorityMap = function() {
      return authorityMap;
    }; 
  });
