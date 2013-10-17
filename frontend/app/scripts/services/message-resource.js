'use strict';

angular.module('db.resources', [])
    .factory('Message', ['CouchbaseResource', function (CouchbaseResource) {
        return CouchbaseResource('./rest/message/:id');
    }]);