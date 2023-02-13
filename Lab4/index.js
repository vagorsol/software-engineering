var express = require('express');
var app = express();

app.use('/square', (req, res) => {
	var inp = req.query.input;
	// if no query, just status w "no number"
	if (!inp) {
		res.send("No number");
	} else {
		
		if (isNaN(inp)) {
			// if it is not a number
			res.json({'status' : 'not a number'});
		} else {
			// if is number, square inp + return as JSON
			res.json({'status' : 'valid', 'result' : inp**2});
		}
	}
	res.send("Something went catastrophically wrong here!"); // if something Weird(tm) happens	
});

// This is the '/test' endpoint that you can use to check that this works
app.use('/test', (req, res) => {
	// create a JSON object
	var data = { 'message' : 'It works!' };
      	// send it back
	res.json(data);
    });

// This just sends back a message for any URL path not covered above
app.use('/', (req, res) => {
	res.send('Default message.');
    });

// This starts the web server on port 3000. 
app.listen(3000, () => {
	console.log('Listening on port 3000');
    });
