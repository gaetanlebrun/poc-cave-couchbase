'use strict';

describe('Controller: VinCtrl', function () {

  // load the controller's module
  beforeEach(module('frontendApp'));

  var VinCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    VinCtrl = $controller('VinCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
