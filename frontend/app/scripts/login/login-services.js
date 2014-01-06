'use strict';

angular.module('login.services', ['http-auth-interceptor'])
    .factory('UserService', function () {
        var user = undefined;
        var UserService = {
            setUser: function (u) {
                user = u;
            },
            getUser: function () {
                return user;
            },
            hasRole: function (role) {
                if (user === undefined) {
                    return false;
                }
                if (user.roles[role] === undefined) {
                    return false;
                }

                return user.roles[role];
            }
        };
        return UserService;
    })
    .factory('LoginService', ['$http', 'authService', 'UserService', function ($http, authService, UserService) {
        var LoginService = {
            login: function (username, password) {
                var data = $.param({username: username, password: password});
                $http.post('auth/login', data,
                    {
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    }).success(function (user) {
                        $http.defaults.headers.common['Auth-Token'] = user.token;
                        UserService.setUser(user);
                        authService.loginConfirmed();
                    });
            },
            logout: function () {
                UserService.setUser(undefined);
                delete $http.defaults.headers.common['Auth-Token'];
            }
        };
        return LoginService;
    }]);