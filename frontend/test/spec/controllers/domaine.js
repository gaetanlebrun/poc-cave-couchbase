'use strict';

describe('Controller: DomaineCtrl', function () {

  // load the controller's module
  beforeEach(module('frontendApp'));

  var DomaineCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DomaineCtrl = $controller('DomaineCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
