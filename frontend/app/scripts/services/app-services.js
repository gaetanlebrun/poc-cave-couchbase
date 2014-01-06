'use strict';

angular.module('app.services', [])
    .provider('crudRoute', ['$routeProvider', function ($routeProvider) {
        this.$get = angular.noop;

        this.routesFor = function (resourceName) {
            var baseUrl = 'views/' + resourceName.toLowerCase();
            var baseRoute = '/' + resourceName.toLowerCase();


            var templateUrl = function (operation) {
                return baseUrl + '/' + resourceName.toLowerCase() + '-' + operation.toLowerCase() + '.html';
            };

            var controllerName = function (operation) {
                return resourceName + operation + 'Ctrl';
            };

            var routeBuilder = {
                whenIndex: function (resolveFns) {
                    routeBuilder.when(baseRoute + '/index', {
                        templateUrl: templateUrl('Index'),
                        controller: controllerName('Index'),
                        resolve: resolveFns
                    });
                    return routeBuilder;
                },
                whenNew: function (resolveFns) {
                    routeBuilder.when(baseRoute + '/new', {
                        templateUrl: templateUrl('Edit'),
                        controller: controllerName('Edit'),
                        resolve: resolveFns
                    });
                    return routeBuilder;
                },
                whenEdit: function (resolveFns) {
                    routeBuilder.when(baseRoute + '/:id', {
                        templateUrl: templateUrl('Edit'),
                        controller: controllerName('Edit'),
                        resolve: resolveFns
                    });
                    return routeBuilder;
                },
                when: function (path, route) {
                    $routeProvider.when(path, route);
                    return routeBuilder;
                },
                otherwise: function (params) {
                    $routeProvider.otherwise(params);
                    return routeBuilder;
                },
                // Access to the core $routeProvider.
                $routeProvider: $routeProvider
            };
            return routeBuilder;
        };
    }]);