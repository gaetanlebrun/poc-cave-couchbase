'use strict';

angular.module('frontendApp')
    .controller('DomaineIndexCtrl', function ($scope, domaines) {
        $scope.domaines = domaines;
    })
    .controller('DomaineEditCtrl', function ($scope, $location, domaine) {
        $scope.domaine = domaine;
        $scope.onDestroy = $scope.onSave = function () {
            $location.path('/domaine/index');
        };
    });
