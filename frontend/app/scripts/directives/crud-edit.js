'use strict';

angular.module('crud.directives')
    .directive('crudEdit', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            scope: true,
            require: '^form',
            link: function (scope, element, attrs, form) {
                var resourceGetter = $parse(attrs.crudEdit);
//                var resourceSetter = resourceGetter.assign;
                var resource = resourceGetter(scope);
                var original = angular.copy(resource);
                var onDestroy = scope.onDestroy || angular.noop;
                var onSave = scope.onSave || angular.noop;
                var onError = scope.onError || angular.noop;

                scope.save = function () {
                    if (resource.id) {
                        resource.update(onSave, onError);
                    } else {
                        resource.save(onSave, onError);
                    }

                };

                scope.canSave = function () {
                    return form.$valid && !angular.equals(original, resource);
                };

                scope.canDestroy = function () {
                    return resource.id;
                };

                scope.destroy = function () {
                    original.destroy(onDestroy, onError);
                };
            }

        }
    }]);