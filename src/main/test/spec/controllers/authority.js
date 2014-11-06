'use strict';

describe('Controller: AuthorityCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var AuthorityCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AuthorityCtrl = $controller('AuthorityCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
