'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Customer
 * @description
 * # Customer
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Customer', function Customer($resource) {

    var DEFAULT_CURR_PAGE_NUM = 1;
    var DEFAULT_NUM_PER_PAGE = 10;

    return $resource('api/customer/:customerId', {}, {
      update: {
        method: 'PUT'
      },
      query: {
        method: 'GET',
        params: {currPageNum: DEFAULT_CURR_PAGE_NUM, numPerPage: DEFAULT_NUM_PER_PAGE},
        transformResponse: function(data) {
          data = eval('(' + data + ')');
          if(data.callStatus == 'SUCCEED') {
            return data;
          }else{
            return null;
          }
        }
      }
    });
  });
