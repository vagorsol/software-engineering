// set up Express
var express = require('express');
var app = express();

// set up BodyParser
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

// import the Person class from Person.js
var Person = require('./Person.js');

/***************************************/

// endpoint for creating a new person
// this is the action of the "create new person" form
app.use('/create', (req, res) => {
	// construct the Person from the form data which is in the request body
	var newPerson = new Person ({
		name: req.body.name,
		age: req.body.age,
	    });

	// save the person to the database
	newPerson.save( (err) => { 
		if (err) {
		    res.type('html').status(200);
		    res.write('uh oh: ' + err);
		    console.log(err);
		    res.end();
		}
		else {
		    // display the "successfull created" message
		    res.send('successfully added ' + newPerson.name + ' to the database');
		}
	    } ); 
    }
    );

// endpoint for showing all the people
app.use('/all', (req, res) => {
    
	// find all the Person objects in the database
	Person.find( {}, (err, persons) => {
		if (err) {
		    res.type('html').status(200);
		    console.log('uh oh' + err);
		    res.write(err);
		}
		else {
		    if (persons.length == 0) {
			res.type('html').status(200);
			res.write('There are no people');
			res.end();
			return;
		    }
		    else {
			res.type('html').status(200);
			res.write('Here are the people in the database:');
			res.write('<ul>');
			// show all the people
			persons.forEach( (person) => {
			    res.write('<li>');
			    res.write('Name: ' + person.name + '; age: ' + person.age);
			    // this creates a link to the /delete endpoint
			    res.write(" <a href=\"/delete?name=" + person.name + "\">[Delete]</a>");
			    res.write('</li>');
					 
			});
			res.write('</ul>');
			res.end();
		    }
		}
	    }).sort({ 'age': 'asc' }); // this sorts them BEFORE rendering the results
});


// IMPLEMENT THIS ENDPOINT!
app.use('/delete', (req, res) => {
	var filter = {'name' : req.query.name};

	Person.findOneAndDelete(filter, (err, person) => {
		if (err){
			res.type('html').status(200);
			console.log('uh oh person DNE' + err);
			res.write(err);
		} else if (!person) {
			// I am not sure if this one is an error tbh
			console.log('Person does not exist?' + err);
		} else {
			console.log('Sucessfully deleted!'); 
		}
		res.end(); 
	})
    res.redirect('/all');
});



// endpoint for accessing data via the web api
// to use this, make a request for /api to get an array of all Person objects
// or /api?name=[whatever] to get a single object
app.use('/api', (req, res) => {

	// construct the query object
	var queryObject = {};
	if (req.query.name) {
	    // if there's a name in the query parameter, use it here
	    queryObject = { "name" : req.query.name };
	}
    
	Person.find( queryObject, (err, persons) => {
		console.log(persons);
		if (err) {
		    console.log('uh oh' + err);
		    res.json({});
		}
		else if (persons.length == 0) {
		    // no objects found, so send back empty json
		    res.json({});
		}
		else if (persons.length == 1 ) {
		    var person = persons[0];
		    // send back a single JSON object
		    res.json( { "name" : person.name , "age" : person.age } );
		}
		else {
		    // construct an array out of the result
		    var returnArray = [];
		    persons.forEach( (person) => {
			    returnArray.push( { "name" : person.name, "age" : person.age } );
			});
		    // send it back as JSON Array
		    res.json(returnArray); 
		}
		
	    });
    });


app.use('/new',(req, res) =>{
	var thisName = req.query.name;
	var thisAge = req.query.age;
	console.log(thisName + " " + thisAge);
	if (!thisName || !thisAge){
		res.json({"status":"error"});
	} else {
		var newPerson = new Person({
			name: thisName,
			age:  thisAge,
		});

		newPerson.save( (err) => {
			if (err) {
				console.log(err);
				res.json({"status":"error"});
			}
			else {
				// display the "successfull created" message
				res.json({"status" : "success"});
			}
		});
	}
});

/*************************************************/

app.use('/public', express.static('public'));

app.use('/', (req, res) => { res.redirect('/public/personform.html'); } );

app.listen(3000,  () => {
	console.log('Listening on port 3000');
    });
