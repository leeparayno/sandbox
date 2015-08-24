
var fs = require('fs');

var file = "";
var ext = "";
//console.log("arg count:" + process.argv.length + "; " + process.argv);
if (process.argv.length== 3) {
	dir = process.argv[2];
} else if (process.argv.length == 4) {
	dir = process.argv[2];
	ext = process.argv[3];
}
//console.log("file:" + file);
var lineCount = 0;

fs.readdir(dir, function(err, list) {
	if (err) throw err;
	for (var n = 0;n < list.length; n++) {
		var file = list[n];
		if (ext != "") {
			//console.log("File checking:" + file)
			if (file.indexOf("." + ext) != -1) {
				console.log(file);
			}
		} else {
			console.log(file);
		}
	}
})


//lineCount++;

