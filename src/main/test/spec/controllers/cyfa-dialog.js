'use strict';

describe('Controller: CyfaDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var CyfaDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CyfaDialogCtrl = $controller('CyfaDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
