'use strict';

describe('Controller: KhzldjdDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var KhzldjdDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    KhzldjdDialogCtrl = $controller('KhzldjdDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
