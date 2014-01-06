'use strict';

angular.module('frontendApp')
    .controller('VinIndexCtrl', ['$scope', 'vins', function ($scope, vins) {
        $scope.vins = vins;
    }])
    .controller('VinEditCtrl', ['$scope', '$http', '$location', 'vin', function ($scope, $http, $location, vin) {
        $scope.vin = vin;
        $scope.onDestroy = $scope.onSave = function () {
            $location.path('/vin/index');
        };

        $http.get('/rest/param/vin/caracteristiques')
            .then(function (response) {
                $scope.types = response.data.types;
                $scope.cepages = response.data.cepages;
            });

        $scope.domaines = function (token) {
            return $http.get('/rest/domaine/by_name?startKey=' + token + '&endKey=' + token + '\u9999')
                .then(function (response) {
                    return response.data;
                });
        };

        $scope.regions = function (token) {
            return $http.get('/rest/param/regions/by_name?startKey=' + token + '&endKey=' + token + '\u9999')
                .then(function (response) {
                    return response.data;
                });
        };

        $scope.$watch('vin.region', function (value) {
            if (value && value.id) {
                $http.get('/rest/param/' + $scope.vin.region.id + '/appellations')
                    .then(function (response) {
                        $scope.appellations = response.data;
                    });
            }
        });
    }]);
