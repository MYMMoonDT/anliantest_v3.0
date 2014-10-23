'use strict';

describe('Controller: XcdcjlDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var XcdcjlDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    XcdcjlDialogCtrl = $controller('XcdcjlDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
