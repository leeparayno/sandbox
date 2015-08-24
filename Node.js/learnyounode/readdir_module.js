var fs = require("fs");

module.exports = function(dir, ext, callback) {
	//readDir(function (err, data) {
		

		//console.log("file:" + file);
		var lineCount = 0;
		var files = new Array();
		var fileCount = 0;
		fs.readdir(dir, function(err, list) {
			if (err) return callback(err); // Early return
			
			for (var n = 0;n < list.length; n++) {
				var file = list[n];
				if (ext) {
					//console.log("File checking:" + file + ", for ext:" + ext)
					if (file.indexOf("." + ext) != -1) {
						//console.log(file + " added");
						files[fileCount] = file;
						fileCount++;
					}
				} else {
					files[fileCount] = file;
					fileCount++;
				}
			}
			//console.log("files returned:" + files.length);
			callback(null,files);			
		})	

	//}) 
}


