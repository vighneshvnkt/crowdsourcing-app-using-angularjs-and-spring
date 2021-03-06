
/**
 * 
 */
'use strict';
// Step 1: declare modules
 angular.module("authModule",[]);
 angular.module("dashboardModule",[]);
 angular.module("personalModule",[]);
 angular.module("skillModule",[]);
 angular.module("projectModule",[]);
 angular.module("homeModule",[]);
 angular.module("registrationModule",[]);
 angular.module("creatorDashboardModule",[]);
 angular.module("donorDashboardModule",[]);
 angular.module("viewProjectModule",[]);
 angular.module("donorProjectViewModule",[]);


 



 angular.module('appCoreModule', [
	 'ngRoute',
     'ngCookies'
 ]);
//Step 2: Register App
var app = angular.module("app", 
		[
		'appCoreModule',
		 'homeModule',
		 'authModule',
		 'dashboardModule',
		 'personalModule',
		 'skillModule',
		 'projectModule',
		 'viewProjectModule',
		 'registrationModule',
		 'creatorDashboardModule',
		 'donorDashboardModule',
		 'donorProjectViewModule'
		 ]);