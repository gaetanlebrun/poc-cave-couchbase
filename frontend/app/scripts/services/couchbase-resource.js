'use strict';

angular.module('db.resources')
    .factory('CouchbaseResource', function ($resource) {
        return function (url) {
            var CouchbaseResource = $resource(url,
                {},
                {
                    update: { method: 'PUT' },
                    save: { method: 'POST' }
                }
            );

            CouchbaseResource.prototype.save = function (cb) {
                return CouchbaseResource.save(this, cb);
            };

            CouchbaseResource.prototype.update = function (cb) {
                return CouchbaseResource.update({id: this.id},
                    angular.extend({}, this, {id: undefined}), cb);
            };

            CouchbaseResource.prototype.destroy = function (cb) {
                return CouchbaseResource.remove({id: this.id}, cb);
            };

            return CouchbaseResource;
        }
    });