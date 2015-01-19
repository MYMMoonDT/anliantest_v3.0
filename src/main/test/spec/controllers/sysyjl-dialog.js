'use strict';

describe('Controller: SysyjlDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var SysyjlDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SysyjlDialogCtrl = $controller('SysyjlDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
