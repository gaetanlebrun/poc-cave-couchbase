'use strict';

angular.module('caveFactories', ['caveResources'])
    .factory('CrudFactory', function ($location) {
        return function serviceFactory(Resource, controller, scope) {
            return {
                get: function (id) {
                    Resource.get({id: id}, function (object) {
                        controller.original = object;
                        controller.setCurrent(new Resource(controller.original));
                    });
                },
                isClean: function () {
                    return angular.equals(controller.original, controller.getCurrent());
                },
                destroy: function () {
                    controller.original.destroy(function () {
                        $location.path(controller.previousPath);
                    });
                },
                save: function () {
                    controller.getCurrent().update(function () {
                        $location.path(controller.previousPath);
                    });
                },
                registerScope: function (scope) {
                    scope.isClean = this.isClean;
                    scope.destroy = this.destroy;
                    scope.save = this.save;
                }

            };
        }
    });