'use strict';

angular.module('frontendApp')
    .controller('IndexCtrl', ['$scope', 'UserService', function ($scope, UserService) {

        $scope.$watch(UserService.getUser, function(newValue, oldValue){
            $scope.isLogged = !(newValue === undefined);
        });

    }]);