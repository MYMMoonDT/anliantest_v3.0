'use strict';

describe('Controller: DepartmentCtrl', function () {

  // load the controller's module
  beforeEach(module('anliantestApp'));

  var DepartmentCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentCtrl = $controller('DepartmentCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
