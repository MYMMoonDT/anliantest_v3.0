'use strict';

describe('Controller: PjfaDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var PjfaDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PjfaDialogCtrl = $controller('PjfaDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
