'use strict';

angular.module('caveServices', ['caveResources'])
    .factory('EditService', function ($location) {
        return function (config, scope, id) {
            return {
                process: function () {
                    var original = {};
                    config.resource.get({id: id}, function (doc) {
                        original = doc;
                        config.setModel(new config.resource(original));
                    });
                    scope.isClean = function () {
                        return angular.equals(original, config.getModel());
                    };
                    scope.destroy = function () {
                        original.destroy(function () {
                            $location.path(config.listPath);
                        });
                    };
                    scope.save = function () {
                        config.getModel().update(function () {
                            $location.path(config.listPath);
                        });
                    };
                }
            }
        }
    })
    .
    factory('NewService', function ($location) {
        return function (config, scope) {
            return {
                process: function () {
                    scope.save = function () {
                        config.resource.save(config.getModel(), function (doc) {
                            $location.path(config.editPath + '/' + doc.id);
                        });
                    }
                }
            }
        }
    })
    .
    factory('FormVinService', function ($http) {
        return function (scope) {
            return {
                process: function () {
                    $http.get('/rest/param/vin/caracteristiques')
                        .then(function (response) {
                            scope.types = response.data.types;
                            scope.cepages = response.data.cepages;
                        });

                    scope.domaines = function (token) {
                        return $http.get('/rest/domaine/by_name?startKey=' + token + '&endKey=' + token + '\u9999')
                            .then(function (response) {
                                return response.data;
                            });
                    };

                    scope.regions = function (token) {
                        return $http.get('/rest/param/regions/by_name?startKey=' + token + '&endKey=' + token + '\u9999')
                            .then(function (response) {
                                return response.data;
                            });
                    };

                    scope.$watch('region', function (value) {
                        if (value && value.id) {
                            scope.vin.region = value.nom;
                            $http.get('/rest/param/' + scope.region.id + '/appellations')
                                .then(function (response) {
                                    scope.appellations = response.data;
                                });
                        }
                    });
                }
            }
        }
    });