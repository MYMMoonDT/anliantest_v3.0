'use strict';

/**
 * @ngdoc overview
 * @name anliantestApp
 * @description
 * # anliantestApp
 *
 * Main module of the application.
 */
angular
  .module('anliantestApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngStorage',
    'dialogs.main',
    'angularFileUpload',
    'ui.bootstrap',
    'datatables'
  ])
  .config(function ($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/project', {
        templateUrl: 'views/project.html',
        controller: 'ProjectCtrl'
      })
      .when('/employee', {
        templateUrl: 'views/employee.html',
        controller: 'EmployeeCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
      $locationProvider.html5Mode(true); 
  })

  .run(function(DTDefaultOptions) {
    DTDefaultOptions.setDisplayLength(10);
    DTDefaultOptions.setLanguage({
      "sProcessing" : "处理中...",
      "sLengthMenu" : "_MENU_ 记录/页",
      "sZeroRecords" : "没有匹配的记录",
      "sInfo" : "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
      "sInfoEmpty" : "显示第 0 至 0 项记录，共 0 项",
      "sInfoFiltered" : "(由 _MAX_ 项记录过滤)",
      "sInfoPostFix" : "",
      "sSearch" : "搜索",
      "sUrl" : "",
      "oPaginate" : {
        "sFirst" : "首页",
        "sPrevious" : "上页",
        "sNext" : "下页",
        "sLast" : "末页"
      }
    })
  });
