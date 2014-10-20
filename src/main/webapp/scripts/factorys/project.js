'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Project
 * @description
 * # Project
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Project', function Project($resource) {

    var DEFAULT_CURR_PAGE_NUM = 1;
    var DEFAULT_NUM_PER_PAGE = 10;

    return $resource('api/project/:projectId', {}, {
      get: {
        method: 'GET',
        transformResponse: function (data) {
          data = eval('(' + data + ')');
          if(data.callStatus == 'SUCCEED') {
            return data.data;
          }else{
            return null;
          }
        }
      },
      save: {
        method: 'POST',
        transformResponse: function (data) {
          data = eval('(' + data + ')');
          if(data.callStatus == 'SUCCEED') {
            return data.data;
          }else{
            return null;
          }
        }
      },
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
