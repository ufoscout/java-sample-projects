  var app = angular.module('app', []);
   
  //define here my first controller
  app.controller('mainCtrl', ['$scope',function($scope) {
    $scope.name = 'Francesco Cina';
  }]);
  