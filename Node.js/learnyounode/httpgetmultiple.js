var http = require('http');
var bl = require('bl');

var URL = new Array();
var urlID = 0;
var workingURLID = 0;
var responses = new Array();
var completedRequests = 0;
if (process.argv.length > 2) {
	for (var i = 2; i < process.argv.length; i++) {
		URL[urlID] = process.argv[i];			
		responses[urlID] = "";
		completedRequests[urlID] = false;
		urlID++;
	}
}



var HttpPage = function retrieve(url) {
	var page = this;
	page.url = url;
	page.data = "";
	
	http.get(page.url, function write(response) {
	//console.log("Got response: " + response.statusCode);
	/*
	response.on("data", function(chunk) {
		console.log(chunk.toString());
	})
	*/
	var characterCount = 0;
	var output = "";
	response.setEncoding('utf8');
	response.pipe(bl(function (err, data) {
		if (err) {
			return console.error(err);
		}
		page.data = data.toString();
			//characterCount += data.toString().length;
			//output += data.toString();
			//responses[workingURLID] = output;
			//console.log(characterCount);
			//console.log(output);		
	}));
	response.on("error", console.error);
	response.on("end", function() {
			//console.log("URL " + workingURLID + " is done.");
	
			
			//completedRequests[workingURLID] = true;
			if (++completedRequests == URL.length) {
				// Last one is done

				/*
				for (var i = 0; i < responses.length; i++) {
					console.log(responses[i].data.toString());
				}
				*/
				responses.forEach(function(aPage) {
					console.log(aPage.url);
				})
			}
			
		})
	}).on("error", function(e) {
		console.log("Error: " + e.message);
	});	
}

function errorReporter(err) {
	console.log(err);
}

function checkComplete() {
	for (var i = 0; i < completedRequests.length; i++) {
		console.log("completedRequests[" + workingURLID + "]: " + completedRequests[workingURLID]);
		if (!completedRequests[workingURLID]) {
			return false;
		}
		return true;
	}	
}

function getURLList(list) {
	for (var i = 0; i < list.length; i++) {
		console.log("Retrieving URL: " + list[i]);
		responses.push(new HttpPage(list[i]));
	}
		
}

getURLList(URL);
