var request = require('request')
var URL = new Array();
var responses = new Array();
var completedRequests = new Array();

if (process.argv.length > 2) {
	var urlID = 0;
	for (var i = 2; i < process.argv.length; i++) {
		URL[urlID] = process.argv[i];			
		responses[urlID] = "";
		completedRequests[urlID] = false;
		urlID++;
	}
}

/**
 * Handle multiple requests at once
 * @param urls [array]
 * @param callback [function]
 * @requires request module for node ( https://github.com/mikeal/request )
 */
var __request = function (urls, callback) {

	'use strict';
	
	var results = {}, t = urls.length, c = 0,
		handler = function (error, response, body) {

			var url = response.request.uri.href;

			results[url] = { error: error, response: response, body: body };

			if (++c === urls.length) { callback(results); }

		};

	while (t--) { request(urls[t], handler); }
};

function output(results) {

	// You can simply iterate all responses
	// to find errors or process the response
	var url, response;

	for (url in responses) {
		// reference to the response object
		response = responses[url];

		console.log(response.response.getHeader("content-type"));

		// find errors
		if (response.error) {
			console.log("Error", url, response.error);
			return;
		}

		// render body
		if (response.body) {
			console.log("Render", url, response.body);
		}
	}	
}

__request(URL, output);