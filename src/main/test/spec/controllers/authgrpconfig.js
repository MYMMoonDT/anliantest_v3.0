'use strict';

describe('Controller: AuthgrpconfigCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var AuthgrpconfigCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AuthgrpconfigCtrl = $controller('AuthgrpconfigCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
