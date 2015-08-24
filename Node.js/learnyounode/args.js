
var total = Number(0);
for (var i = 2; i < process.argv.length; i++) {
	var arg = Number(process.argv[i]);
	total += arg;
}
console.log(total)