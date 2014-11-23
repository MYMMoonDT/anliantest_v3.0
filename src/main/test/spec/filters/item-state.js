'use strict';

describe('Filter: itemState', function () {

  // load the filter's module
  beforeEach(module('anliantestApp'));

  // initialize a new instance of the filter before each test
  var itemState;
  beforeEach(inject(function ($filter) {
    itemState = $filter('itemState');
  }));

  it('should return the input prefixed with "itemState filter:"', function () {
    var text = 'angularjs';
    expect(itemState(text)).toBe('itemState filter: ' + text);
  });

});
