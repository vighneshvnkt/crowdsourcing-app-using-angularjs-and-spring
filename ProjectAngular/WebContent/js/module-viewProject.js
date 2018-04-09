/**
 * 
 */
 
var viewProjectModule = angular.module('viewProjectModule');
viewProjectModule.controller('viewProjectController', function($scope,$rootScope,$location,viewProjectService) {
	var viewProjectCtrl = this;
	
	var project1 = $rootScope.project;
	console.log(project1);
	
	
	viewProjectCtrl.projectToDelete = {
			projectName : project1.name,
			 projectDescription : project1.desc,
			 projectDeadline : project1.deadline,
			 amountRequired : project1.requiredAmount,
			 amountReceived : project1.receivedAmount,
			 projectCategory : project1.category.catName	
	};
	
	viewProjectCtrl.deleteProject = function() {
		console.log(viewProjectCtrl.projectToDelete);
		
		viewProjectService.deleteProject($rootScope.userSession.id,viewProjectCtrl.projectToDelete, callbackSuccess
				,callBackError);
	}
	
	viewProjectCtrl.message = "";
	viewProjectCtrl.error = false;
	

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		if (data.success) {
			viewProjectCtrl.openComponentModal('Successfully deleted project');
			viewProjectCtrl.message = "Deleted project";
			viewProjectCtrl.error = true;
			
			

		} else {
			viewProjectCtrl.message = data.message;
			viewProjectCtrl.error = true;
		}

	};

	var callBackError = function(data, headers) {
		viewProjectCtrl.message = data.message;
		viewProjectCtrl.error = true;

	};

	
});


viewProjectModule.factory('viewProjectService', function($http,$timeout,APP_CONSTANT) {
	var viewProjectService = {};
	
	viewProjectService.deleteProject = function (id,data, callback,callBackError) {
		
	
	if(APP_CONSTANT.DEMO){
		$timeout(function(){
	         	
	     		var response;
	     		
	     			response = [
	     						{
	     							"name": "C",
	     							"desc": null
	     						},
	     						{
	     							"name": "C++",
	     							"desc": null
	     						}
	     					];
	     	
	
	         callback(response);
	     }, 1000);
		}else{
			
			/*
			 * Use this for real authentication
			 * ----------------------------------------------
			 */
			$http.post(APP_CONSTANT.REMOTE_HOST + '/admin/'+id+'/deleteProject/', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { // IF
				// STATUS
				// CODE
				// NOT
				// 200
				callBackError(data, headers);
			});
			
		}
	};
	
	
	return viewProjectService;
});