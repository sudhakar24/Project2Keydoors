/**
 * 
 */
app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	
	if($rootScope.currentUser!=undefined){
		UserService.getUser().then(function(response){
			$scope.user=response.data
		},function(response){
			if(response.status==401){
				$location.path('/login')
				
			}
			if(reponse.status==500){
				$scope.error=response.data
				$location.path('/editprofile')
			}
		})
	}
	$scope.registerUser=function(){  //2
		console.log($scope.user)
		UserService.registerUser($scope.user) //3
		.then(function(response){
			$location.path('/home')
		},function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.error=response.data   
		})  	
	}
	$scope.login = function() {
		
		UserService.login($scope.user).then(function(response) {
			$rootScope.currentUser=response.data
			$cookieStore.put('currentUser',response.data)
			$location.path('/home')
		}, function(response) {
			if (response.status == 401){
				console.log(response.data)
				console.log(response.status)
				$scope.error = response.data
			$location.path('/login')
			}
		})
	}
	$scope.editUserProfile=function(){
		UserService.editUserProfile($scope.user).then(function(response){
			alert('Updated Successfully')
			$location.path('/home')
		},function(response){
			if(response.status==401){
				$location.path('/login')}
			if(response.status==500){
				$scope.error=response.data
				$location.path('/editprofile')
			}
			
		})
		}
	
})