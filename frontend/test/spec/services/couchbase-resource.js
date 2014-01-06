'use strict';

describe('Service: couchbaseResource', function () {

  // load the service's module
  beforeEach(module('frontendApp'));

  // instantiate service
  var couchbaseResource;
  beforeEach(inject(function (_couchbaseResource_) {
    couchbaseResource = _couchbaseResource_;
  }));

  it('should do something', function () {
    expect(!!couchbaseResource).toBe(true);
  });

});
