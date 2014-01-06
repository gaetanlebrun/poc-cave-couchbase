'use strict';

angular.module('db.resources')
    .factory('Vin', ['CouchbaseResource', function (CouchbaseResource) {
        return CouchbaseResource('./rest/vin/:id');
    }]);