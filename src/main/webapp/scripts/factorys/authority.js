'use strict';

/**
 * @ngdoc factory
 * @name anliantestApp.Authority
 * @description
 * # Authority
 * Factory in the anliantestApp.
 */
angular.module('anliantestApp')
  .factory('Authority', function Authority($resource) {
    return $resource('api/authority',{}, {
      groupAll: {
        method: 'GET',
        url: 'api/authority/group/all'
      },
      addGroup: {
        method: 'POST',
        url: 'api/authority/group/add'
      },
      updateGroup: {
        method: 'POST',
        url: 'api/authority/group/update'
      },
      deleteGroup: {
        method: 'DELETE',
        url: 'api/authority/group/delete/:id'
      },
      itemAll: {
        method: 'GET',
        url: 'api/authority/item/all'
      },
    });
  });