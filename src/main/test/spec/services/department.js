'use strict';

describe('Service: department', function () {

  // load the service's module
  beforeEach(module('anliantestApp'));

  // instantiate service
  var department;
  beforeEach(inject(function (_department_) {
    department = _department_;
  }));

  it('should do something', function () {
    expect(!!department).toBe(true);
  });

});
