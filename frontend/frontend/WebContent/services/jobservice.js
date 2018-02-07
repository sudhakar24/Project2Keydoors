/**
 * 
 */
app.factory('JobService',function($http){
	var jobService={}
	var BASE_URL="http://localhost:8082/middleware"
	jobService.addJob=function(job){
		return $http.post(BASE_URL + '/savejob',job);
	}
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL + '/alljobs')
	}
	jobService.getJob=function(jobId){
		return $http.get(BASE_URL + '/getjob/'+jobId)
	}
	return jobService;
})