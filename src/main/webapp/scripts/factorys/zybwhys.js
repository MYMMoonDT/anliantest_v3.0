'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.ZYBWHYS
 * @description
 * # ZYBWHYS
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('ZYBWHYS', function ZYBWHYS($resource) {
    return $resource('api/zybwhys/:zybwhysId',{},{
      query: {
        method: 'GET',
        url: 'api/zybwhys/all',
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
