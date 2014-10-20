'use strict';

describe('Directive: atDatepicker', function () {

  // load the directive's module
  beforeEach(module('anliantestApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<at-datepicker></at-datepicker>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the atDatepicker directive');
  }));
});
