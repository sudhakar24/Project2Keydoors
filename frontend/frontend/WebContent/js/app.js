/**
 * 
 */
var app = angular.module("app", [ 'ngRoute','ngCookies' ])
app.config(function($routeProvider) {
	$routeProvider.when('/register', {
		templateUrl : 'views/reg.html',
		controller : 'UserController'
	}).when('/login', {
		templateUrl : 'views/loginpage.html',
		controller : 'UserController'
	}).when('/name',{
		templateUrl :'views/home.html'
	}).when('/editprofile',{
		templateUrl :'views/editprofile.html',
		controller :'UserController'
	}).when('/addjob',{
		templateUrl:'views/jobform.html',
		controller :'JobController'
	}).when('/alljobs',{
		templateUrl:'views/jobslist.html',
		controller :'JobController'
	}).when('/addblog',{
		templateUrl:'views/blogform.html', //V to Controller
		controller:'BlogPostController'
	})
	.when('/getblogs',{
		templateUrl:'views/bloglist.html',//Controller to V
		controller:'BlogPostController'
	})
	.when('/admin/getblog/:id',{
		templateUrl:'views/approvalform.html',
		controller:'BlogPostDetailsController'
	})
	.when('/getblog/:id',{
		templateUrl:'views/blogdetails.html',
		controller:'BlogPostDetailsController'
	})
	.when('/updateprofile',{
		templateUrl:'views/uploadprofilepic.html'
	})
	.when('/suggestedusers', {
        templateUrl : 'views/suggestedusers.html',
        controller:'FriendController'
    })
    .when('/pendingrequests',{
		templateUrl:'views/pendingRequests.html',
		controller:'FriendController'
	})
	.when('/getfriends',{
		templateUrl:'views/listoffriends.html',
		controller:'FriendController'
	})
	/*.when("/friendprofile/:id", {
        templateUrl : 'views/profile.html',
        controller:'FriendController'
    })
	.when('/chat',{
		templateUrl:'views/chat.html',
		controller:'ChatController'
	})
	*/.when('/home',{
		templateUrl:'views/home.html',
		controller:'HomeController'
	})
	.otherwise({
		templateUrl : 'views/home.html',
		controller:'HomeController'
	})
})
app.run(function($rootScope, $cookieStore, UserService, $location) {
	if ($rootScope.currentUser == undefined)
		$rootScope.currentUser = $cookieStore.get('currentUser')
	
		
		$rootScope.logout = function() {
		UserService.logout().then(function(response) {
			delete $rootScope.currentUser;
			$cookieStore.remove('currentUser')
			$location.path('/login')
		}, function(response) {
			console.log(response.status)
			$location.path('/login')
		})
	}
})