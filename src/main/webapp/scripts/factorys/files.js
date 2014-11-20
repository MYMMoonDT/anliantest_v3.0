'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Files
 * @description
 * # Files
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Files', function Files($resource) {

    var DEFAULT_CURR_PAGE_NUM = 1;
    var DEFAULT_NUM_PER_PAGE = 10;

    return $resource('api/files/:fileGroupId', {}, {
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
