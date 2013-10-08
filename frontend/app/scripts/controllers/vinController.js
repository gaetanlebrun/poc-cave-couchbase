'use strict';

angular.module('frontendApp')
    .controller('IndexCtrl', function ($scope) {
        $scope.previousPath = null;
        $scope.$on('$locationChangeStart', function (evt, absNewUrl, absOldUrl) {
            $scope.previousPath = absOldUrl;
            //console.log('start', evt, absNewUrl, absOldUrl);
        });
    });

angular.module('frontendApp')
    .controller('VinsCtrl', function ($scope, Vin) {
        $scope.vins = Vin.query();
    })
    .controller('NewVinCtrl', function ($scope, $location, Vin) {
        $scope.save = function () {
            Vin.save($scope.vin, function (vin) {
                $location.path('/edit/vin/' + vin.id);
            });
        }
    })
    .controller('EditVinCtrl', function ($scope, $location, $routeParams, CrudFactory, Vin) {
        var self = this;
        self.original = {};
        self.previousPath = '/list/vins';
        self.getCurrent = function () {
            return $scope.vin;
        };
        self.setCurrent = function (vin) {
            return $scope.vin = vin;
        };

        var service = CrudFactory(Vin, self);
        service.registerScope($scope);
        service.get($routeParams.id);
    });