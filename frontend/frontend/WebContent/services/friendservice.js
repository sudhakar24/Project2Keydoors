/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={}
	
	var BASE_URL="http://localhost:8082/middleware"
		
		friendService.suggestedusers=function(){
		return	$http.get(BASE_URL + "/suggestedusers")
		}
		
	friendService.sendFriendRequest=function(toId){
		return $http.post(BASE_URL +"/friendrequest/"+toId)
	}
	
	friendService.getAllPendingRequests=function(){
		return $http.get(BASE_URL + "/pendingrequests")
	}
	friendService.updatePendingRequest=function(pendingrequest){
		return $http.put(BASE_URL + "/updatependingrequest",pendingrequest)
	}
	friendService.getListOfFriends=function(){
		return $http.get(BASE_URL + "/getfriends")
	}
	/*friendService.getUserDetails=function(id){
		return $http.get(BASE_URL + "/getuserdetails/"+id)
	}
	
	
	
	friendService.getMutualFriends=function(suggestedUsers){
		return $http.put(BASE_URL + "/getmutualfriends",suggestedUsers)
	}
	
	friendService.getMutualFriendsbyfriends=function(friends){
		return $http.put(BASE_URL + "/getmutualfriendsbyfriends",friends)
	}
	
	friendService.getallmutualfriends=function(friend){
		return $http.put(BASE_URL +"/getallmutualfriends/"+friend)
	}
	
	friendService.getuserblogpost=function(friend){
		return $http.get(BASE_URL +"/getBlogpostByuserid/"+friend)
	}*/
	

	return friendService;
})