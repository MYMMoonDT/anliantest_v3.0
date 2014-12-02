'use strict';

describe('Service: authority', function () {

  // load the service's module
  beforeEach(module('anliantestApp'));

  // instantiate service
  var authority;
  beforeEach(inject(function (_authority_) {
    authority = _authority_;
  }));

  it('should do something', function () {
    expect(!!authority).toBe(true);
  });

});
