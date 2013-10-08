'use strict';




angular.module('frontendApp', ['caveResources','caveServices','ui.bootstrap'])
//angular.module('frontendApp', ['ngResource'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html'
            })
            .when('/list/vins', {
                templateUrl: '/views/list-vins.html',
                controller: 'VinsCtrl'
            })
            .when('/new/vin', {
                templateUrl: '/views/edit-vin.html',
                controller: 'NewVinCtrl'
            })
            .when('/edit/vin/:id', {
                templateUrl: '/views/edit-vin.html',
                controller: 'EditVinCtrl'
            })
            .when('/list/domaines', {
                templateUrl: '/views/list-domaines.html',
                controller: 'DomainesCtrl'
            })
            .when('/new/domaine', {
                templateUrl: '/views/edit-domaine.html',
                controller: 'NewDomaineCtrl'
            })
            .when('/edit/domaine/:id', {
                templateUrl: '/views/edit-domaine.html',
                controller: 'EditDomaineCtrl'
            })
            .when('/contact', {
                templateUrl: '/views/edit-contact.html',
                controller: 'ContactCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
