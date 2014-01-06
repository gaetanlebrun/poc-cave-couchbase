'use strict';

angular.module('crud.directives')
    .directive('crudButtons', ['$parse', function () {
        return {
            restrict: 'E',
            replace: true,
            template: '<div class="control-group">' +
                '<a href="{{cancel}}" class="btn">Cancel</a>' +
                '<button ng-click="save()" ng-disabled="!canSave()" class="btn btn-primary">Save</button>' +
                '<button ng-click="destroy()" ng-show="canDestroy()" class="btn btn-danger">Delete</button>' +
                '</div>' +
                '</div>',
            link: function (scope, element, attrs) {
                scope.cancel = attrs.cancel;
            }
        };
    }]);
