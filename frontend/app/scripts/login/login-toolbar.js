'use strict';

angular.module('login.toolbar', ['ui.bootstrap', 'login.services'])
    .controller('LoginModalCtrl', ['$scope', '$modalInstance', 'LoginService', function ($scope, $modalInstance, LoginService) {
        $scope.credentials = {username: undefined, password: undefined};

        $scope.login = function () {
            LoginService.login($scope.credentials.username, $scope.credentials.password);
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        }
    }])
    .directive('loginToolbar', ['$modal', '$http', 'LoginService', 'UserService', function ($modal, $http, LoginService, UserService) {
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'views/login/login-toolbar.html',
            link: function (scope, elem, attrs) {
                var modalInstance;

                scope.$watch(UserService.getUser, function (newValue, oldValue) {
                    scope.user = newValue;
                });

                scope.$on('event:auth-loginRequired', function () {
                    scope.openLoginModal();
                });

                scope.$on('event:auth-loginConfirmed', function () {
                    console.info('logged in');
                });

                scope.logout = function () {
                    LoginService.logout();
                };

                scope.openLoginModal = function () {
                    modalInstance = $modal.open({
                        backdrop: 'true',
                        templateUrl: 'views/login/login.html',
                        controller: 'LoginModalCtrl'
                    });
                };
            }
        }
    }]);