var express = require('express');
var app = express();

// This is the definition of the Product class -- DO NOT CHANGE IT!
class Product {
    constructor(id, status) {
		this.id = id;
		this.status = status;
    }
}

// This is the map of IDs to Product objects -- DO NOT CHANGE IT!
var products = new Map();
products.set('1234', new Product('1234', 'available'));
products.set('5678', new Product('5678', 'out of stock'));
products.set('1111', new Product('1111', 'discontinued'));
products.set('4321', new Product('4321', 'available'));
products.set('5555', new Product('5555', 'pre-order'));
products.set('3530', new Product('3530', 'discontinued'));


// This is the '/test' endpoint that you can use to check that this works
// Do not change this, as you will want to use it to check the test code in Part 2
app.use('/test', (req, res) => {
	// create a JSON object
	var data = { 'message' : 'It works!' };
    // send it back
	res.json(data);
    });


// This is the endpoint you need to implement in Part 1 of this assignment
app.use('/status', (req, res) => {
	var ids = req.query.id; 
	var productstats = [];

	// check if an actual id was submitted or not
	if (!ids || isNaN(ids)) {
		console.log("No ID entered");
		res.json(productstats); // send back empty array?
	} else {
		// check is an array of ids were submitted or not
		if (Array.isArray(ids)){
			console.log("YES ARRAY");
			ids.forEach((i) => {
				console.log(productstats);

				if (products.get(i)) {
					// add product + status if in map
					productstats.push(products.get(i)); 
				} else {
					// if the product isn't in the map, don't add
					productstats.push({
						"id" : i,
						"status" : "discontinued"
					});
				}
				
			})
		} else {
			if (products.get(ids)) {
				// add product + status if in map
				productstats.push(products.get(ids)); 
			} else {
				// if the product isn't in the map, don't add
				productstats.push({
					"id" : ids,
					"status" : "discontinued"
				});
			}
		}
	}
	// console.log(productstats);
	res.send(productstats);

    });


// -------------------------------------------------------------------------
// DO NOT CHANGE ANYTHING BELOW HERE!



// This just sends back a message for any URL path not covered above
app.use('/', (req, res) => {
	res.send('Default message.');
    });

// This starts the web server on port 3000. 
app.listen(3000, () => {
	console.log('Listening on port 3000');
    });
