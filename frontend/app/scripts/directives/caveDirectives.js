'use strict';

angular.module('caveDirectives', [])
    .directive("userTypeahead", function () {

        var searchUsers = _.debounce(function (query, process) {
            $.get('/api/users.json', { q: query }, function (data) {
                var userLabels = [];
                var userMapped = {};

                _.each(data, function (item, ix, label) {
                    if (_.contains(userMapped, item.label)) {
                        item.label = item.label + ' #' + item.value;
                    }
                    userMapped[item.label] = item.value;
                    userLabels.push(item.label);
                });

                process(userLabels);
            });
        }, 300);

        return {
            restrict: 'A'
            ,link: function(scope, element, attrs){

                element.typeahead({
                    source: searchUsers
                    , updater: function (item) {
                        scope.$apply(function(){
                            scope.notifyList.push({
                                userId: userMapped[item]
                                ,name: item
                            });
                        });
                        return '';
                    }
                    , items: 10
                });

            }
        };
    });