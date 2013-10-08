'use strict';

//angular.module('frontendApp')
//    .controller('IndexCtrl', function ($scope) {
//        $scope.previousPath = null;
//        $scope.$on('$locationChangeStart', function (evt, absNewUrl, absOldUrl) {
//            $scope.previousPath = absOldUrl;
//            //console.log('start', evt, absNewUrl, absOldUrl);
//        });
//    })

angular.module('frontendApp')
//    .controller('HeaderCtrl', function ($scope, $location) {
//        $scope.isActive = function (viewLocation) {
//            return viewLocation === $location.path();
//        };
//    })
    .factory('VinCtrlConfig', function (Vin) {
        return function (scope) {
            scope.vin = {};
            return {
                setModel: function (model) {
                    scope.vin = model;
                    scope.region = model.region;
                },
                getModel: function () {
                    return scope.vin;
                },
                editPath: '/edit/vin',
                listPath: '/list/vins',
                resource: Vin
            }
        }
    })
    .controller('VinsCtrl', function ($scope, Vin) {
        $scope.vins = Vin.query();
    })
    .controller('NewVinCtrl', function ($scope, $http, VinCtrlConfig, NewService, FormVinService) {
        NewService(VinCtrlConfig($scope), $scope).process();
        FormVinService($scope).process();
    })
    .controller('EditVinCtrl', function ($scope, $routeParams, VinCtrlConfig, EditService, FormVinService) {
        EditService(VinCtrlConfig($scope), $scope, $routeParams.id).process();
        FormVinService($scope).process();
    });

// Contrôleurs Domaine
angular.module('frontendApp')
    .factory('DomaineCtrlConfig', function (Domaine) {
        return function (scope) {
            return {
                setModel: function (model) {
                    scope.domaine = model;
                },
                getModel: function () {
                    return scope.domaine;
                },
                editPath: '/edit/domaine',
                listPath: '/list/domaines',
                resource: Domaine
            }
        }
    })
    .controller('DomainesCtrl', function ($scope, Domaine) {
        $scope.domaines = Domaine.query();
    })
    .controller('NewDomaineCtrl', function ($scope, DomaineCtrlConfig, NewService) {
        NewService(DomaineCtrlConfig($scope), $scope).process();
    })
    .controller('EditDomaineCtrl', function ($scope, $routeParams, DomaineCtrlConfig, EditService) {
        EditService(DomaineCtrlConfig($scope), $scope, $routeParams.id).process();
    });

// Contrôleur Contact
angular.module('frontendApp')
    .controller('ContactCtrl', function ($scope, Message) {
        $scope.message = {};
        $('.alert').hide();

        $scope.save = function () {
            Message.save($scope.message, function () {
                $scope.message = {};
                $('.alert').show();
            });
        };

        $scope.hideAlert = function () {
            $('.alert').hide();
        }
    });