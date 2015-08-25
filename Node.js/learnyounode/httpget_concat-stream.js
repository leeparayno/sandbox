var http = require('http');
var concatStream = require('concat-stream');

var URL = "";
if (process.argv.length > 2) {
	URL = process.argv[2];
}

function write(response) {
	//console.log("Got response: " + response.statusCode);
	/*
	response.on("data", function(chunk) {
		console.log(chunk.toString());
	})
	*/
	var characterCount = 0;
	var output = "";
	response.pipe(concatStream(function (data) {
		characterCount += data.toString().length;
		output += data.toString();
		console.log(characterCount);
		console.log(output);		
	}))
}

function errorReporter(err) {
	console.log(err);
}

var e = "";
http.get(URL, write).on("error", function(e) {
	console.log("Error: " + e.message);
});
/* Official
    var http = require('http')
    
    http.get(process.argv[2], function (response) {
      response.setEncoding('utf8')
      response.on('data', console.log)
      response.on('error', console.error)
    })
*/