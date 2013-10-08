'use strict';

angular.module('caveResources', ['ngResource'])
    .factory('CrudResource', function ($resource) {
        return function (url) {
            var CrudResource = $resource(url,
                {},
                {
                    update: { method: 'PUT' }
                }
            );

            CrudResource.prototype.update = function (cb) {
                return CrudResource.update({id: this.id},
                    angular.extend({}, this, {id: undefined}), cb);
            };

            CrudResource.prototype.destroy = function (cb) {
                return CrudResource.remove({id: this.id}, cb);
            };

            return CrudResource;
        }
    })
    .factory('Message', function (CrudResource) {
        return CrudResource('./rest/message');
    })
    .factory('Domaine', function (CrudResource) {
        return CrudResource('./rest/domaine/:id');
    })
    .factory('Vin', function (CrudResource) {
        return CrudResource('./rest/vin/:id');
    });