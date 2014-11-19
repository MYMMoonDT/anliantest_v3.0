'use strict';

angular.module('anliantestApp')
  .factory('loadingInterceptor', function loadingInterceptor($q, $timeout) {
    return {
      'request': function(config) {
        $.isLoading();
        return config || $q.when(config);
      },
      'requestError': function(config) {
        $timeout(function() {
          $.isLoading('hide');
        }, 300);
        if (canRecover(rejection)) {
          return responseOrNewPromise
        }
        return $q.reject(rejection);
      },
      'response': function(response) {
        $timeout(function() {
          $.isLoading('hide');
        }, 300);
        return response || $q.when(response);
      },
      'responseError': function(rejection) {
        $timeout(function() {
          $.isLoading('hide');
        }, 300);
        if (canRecover(rejection)) {
          return responseOrNewPromise
        }
        return $q.reject(rejection);
      }
    };
  });