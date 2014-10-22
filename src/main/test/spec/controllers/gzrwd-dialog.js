'use strict';

describe('Controller: GzrwdDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var GzrwdDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    GzrwdDialogCtrl = $controller('GzrwdDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
