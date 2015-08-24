var http = require('http');

var URL = "";
//console.log("arg count:" + process.argv.length + "; " + process.argv);
if (process.argv.length > 2) {
	URL = process.argv[2];
}

function write(response) {
	console.log("Got response: " + response.statusCode);
	
	response.on("data")
}

function errorReporter(err) {
	console.log(err);
}

var e = "";
http.get(URL, write).on("error", errorReporter(e));