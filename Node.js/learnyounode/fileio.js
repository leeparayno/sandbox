
var fs = require('fs');

var file = "";
//console.log("arg count:" + process.argv.length + "; " + process.argv);
if (process.argv.length > 2) {
	file = process.argv[2];
}
//console.log("file:" + file);
var lineCount = 0;


try {
	var fileContent = fs.readFileSync(file);
	var lines = fileContent.toString().split("\n");
	//console.log("fileContent:" + fileContent);
	//console.log("lines inside:" + lines)
	lineCount = lines.length - 1;	
} catch (err) {
	console.error(err);
}

//lineCount++;

console.log(lineCount);