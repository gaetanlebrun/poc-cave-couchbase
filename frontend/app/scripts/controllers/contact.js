'use strict';

angular.module('frontendApp')
    .controller('ContactEditCtrl', function ($scope, Message) {
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
