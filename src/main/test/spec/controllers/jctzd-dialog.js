'use strict';

describe('Controller: JctzdDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var JctzdDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    JctzdDialogCtrl = $controller('JctzdDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
