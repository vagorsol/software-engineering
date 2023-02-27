var express = require('express');
var app = express();

app.use('/sum', (req, res) => {

	var vals = req.query.val;
	var sum = 0;
 
	if (vals) {
		// check if vals *is* an array
		if (Array.isArray(vals)) {
			vals.forEach( (v) => {
				// console.log(v);
				if (!isNaN(v)) {
					sum += v; // how to add without appending?
					console.log(sum + v);
				}
			});
		} else {
			// if vals is a single entry & is a number
			if (!isNaN(vals)) {
				sum = vals;
			}
		}
	}

	res.json( {'sum' : sum} );
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
