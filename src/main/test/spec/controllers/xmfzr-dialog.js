'use strict';

describe('Controller: XmfzrDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var XmfzrDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    XmfzrDialogCtrl = $controller('XmfzrDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
