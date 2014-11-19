'use strict';

describe('Directive: atDualListbox', function () {

  // load the directive's module
  beforeEach(module('anliantestApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<at-dual-listbox></at-dual-listbox>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the atDualListbox directive');
  }));
});
