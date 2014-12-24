'use strict';

describe('Controller: ProgressDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var ProgressDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ProgressDialogCtrl = $controller('ProgressDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});