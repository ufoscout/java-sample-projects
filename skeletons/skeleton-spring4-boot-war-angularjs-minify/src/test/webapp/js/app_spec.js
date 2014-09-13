'use strict';

/* jasmine specs for controllers go here */
describe('Angularjs - Controller tests', function () {

   describe('HomeController', function () {
      var scope, ctrl;

      beforeEach(module('app'));
      beforeEach(inject(function ($rootScope, $controller) {
         scope = $rootScope.$new();
         ctrl = $controller('mainCtrl', {$scope: scope});
      }));


      it('name should be Francesco Cina', function () {
         expect(scope.name).toBe('Francesco Cina');
      });
   });
});