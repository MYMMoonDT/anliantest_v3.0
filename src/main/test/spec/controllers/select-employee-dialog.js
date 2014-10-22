'use strict';

describe('Controller: SelectEmployeeDialogCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var SelectEmployeeDialogCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SelectEmployeeDialogCtrl = $controller('SelectEmployeeDialogCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
