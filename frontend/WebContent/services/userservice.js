/**
 * 
 */
app.factory('UserService', function($http) {
	var BASE_URL = "http://localhost:8082/middleware"

	var userService = {}

	userService.registerUser = function(user) {

		console.log($http.user)
		return $http.post(BASE_URL + "/registeruser", user)

	}
	userService.login = function(user) {
		return $http.post(BASE_URL + "/login", user)
	}
	userService.logout = function() {
		return $http.get(BASE_URL + "/logout")
	}
	userService.getUser = function() {
		return $http.get(BASE_URL + "/getUser")
	}
	userService.editUserProfile = function(user) {
		return $http.put(BASE_URL + "/edituseruser", user)
	}
	return userService;
})