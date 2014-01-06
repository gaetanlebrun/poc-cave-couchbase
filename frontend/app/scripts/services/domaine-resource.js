'use strict';

angular.module('db.resources')
    .factory('Domaine', ['CouchbaseResource', function (CouchbaseResource) {
        return CouchbaseResource('./rest/domaine/:id');
    }]);