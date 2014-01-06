'use strict';


angular.module('frontendApp', [
        'db.resources',
        'app.services',
        'crud.directives',
        'login.toolbar'
    ])
    .factory('GetResourcePromise', ['$q', function ($q) {
        return function (resource, id) {
            var gettingRessource = $q.defer();
            resource.get({id: id},
                function (doc, responseHeader) {
                    gettingRessource.resolve(doc);
                },
                function error(httpResponse) {
                    gettingThing.reject(httpResponse)
                });
            return gettingRessource.promise
        }
    }])
    .config(['$routeProvider', 'crudRouteProvider', function ($routeProvider, crudRouteProvider) {
        crudRouteProvider
            .routesFor('Vin')
            .whenIndex({
                vins: ['Vin', function (Vin) {
                    return Vin.query();
                }]
            })
            .whenEdit({
                vin: ['$route', 'GetResourcePromise', 'Vin', function ($route, GetResourcePromise, Vin) {
                    return GetResourcePromise(Vin, $route.current.params.id);
                }]
            })
            .whenNew({
                vin: ['Vin', function (Vin) {
                    return new Vin({});
                }]
            });
        crudRouteProvider
            .routesFor('Domaine')
            .whenIndex({
                domaines: ['Domaine', function (Domaine) {
                    return Domaine.query();
                }]
            })
            .whenEdit({
                domaine: ['$route', 'GetResourcePromise', 'Domaine', function ($route, GetResourcePromise, Domaine) {
                    return GetResourcePromise(Domaine, $route.current.params.id);
                }]
            })
            .whenNew({
                domaine: ['Domaine', function (Domaine) {
                    return new Domaine({});
                }]
            })
            .when('/', {
                templateUrl: 'views/main.html'
            })
            .when('/contact', {
                templateUrl: '/views/contact/contact-edit.html',
                controller: 'ContactEditCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);
