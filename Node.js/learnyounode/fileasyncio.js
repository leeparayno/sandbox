
var fs = require('fs');

var file = "";
//console.log("arg count:" + process.argv.length + "; " + process.argv);
if (process.argv.length > 2) {
	file = process.argv[2];
}
//console.log("file:" + file);
var lineCount = 0;

fs.readFile(file, function(err, data) {
	if (err) throw err;
	var lines = data.toString().split("\n");
	lineCount = lines.length - 1;
	console.log(lineCount);
})


//lineCount++;

