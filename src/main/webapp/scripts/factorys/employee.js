'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Employee
 * @description
 * # Employee
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Employee', function Employee($resource) {

    var DEFAULT_CURR_PAGE_NUM = 1;
    var DEFAULT_NUM_PER_PAGE = 10;

    return $resource('api/employee/:employeeId', {}, {
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
