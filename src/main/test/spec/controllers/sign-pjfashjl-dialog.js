'use strict';

describe('Controller: SignPjfashjlDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var SignPjfashjlDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SignPjfashjlDialogCtrl = $controller('SignPjfashjlDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
