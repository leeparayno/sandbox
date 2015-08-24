
var readDirLee = require("./readdir_module.js");

var file = "";
var ext = "";
//console.log("arg count:" + process.argv.length + "; " + process.argv);
if (process.argv.length== 3) {
	dir = process.argv[2];
} else if (process.argv.length == 4) {
	dir = process.argv[2];
	ext = process.argv[3];
}

function output(err, data) {
	for (var n = 0;n < data.length; n++) {
		console.log(data[n]);
	}
}


readDirLee(dir, ext, output);
