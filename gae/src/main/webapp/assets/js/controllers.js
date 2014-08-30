'use strict';

/* Controllers */

function SongListCtrl($scope, $http, $resource) {
	$http.get('/rest/songs/all').success(function(data) {
		$scope.songs = data;
	});

	var Song = $resource('/rest/crud/song/:id', {
		id : '@id'
	});
	
	$scope.newSong = new Song();
	
	$scope.addSong = function() {
		$scope.newSong.$save(
			function(song) {
				$scope.songs.push(song);
				$scope.newSong = new Song();
			}, 
			function(response) {
				$scope.violations = response.data;
			} 
		);
		
	};
	
}

// PhoneListCtrl.$inject = ['$scope', 'Phone'];

