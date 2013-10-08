'use strict';


angular.module('frontendApp')
    .controller('DomainesCtrl', function ($scope, Domaine) {
        $scope.domaines = Domaine.query();
    })
    .controller('NewDomaineCtrl', function ($scope, $location, $window, Domaine) {
        $scope.save = function () {
            Domaine.save($scope.domaine, function (domaine) {
                $location.path('/edit/domaine/' + domaine.id);
            });
        }
    })
    .controller('EditDomaineCtrl', function ($scope, $location, $routeParams, CrudFactory, Domaine) {
        var self = this;
        self.original = {};
        self.previousPath = '/list/domaines';
        self.getCurrent = function () {
            return $scope.domaine;
        };
        self.setCurrent = function (domaine) {
            return $scope.domaine = domaine;
        };

        var service = CrudFactory(Domaine, self);
        service.registerScope($scope);
        service.get($routeParams.id);
    });