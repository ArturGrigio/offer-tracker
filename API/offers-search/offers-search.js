'use strict';

var mysql = require('mysql');

/*
    Return offer search results that meet the specified criteria
*/
exports.handler = (event, context, callback) => {
    console.log('Received event:', JSON.stringify(event, null, 2));
    queryDatabase();
    callback(null, {
        body: { "message" : "success!"}
    });

};

function queryDatabase() {
    var connection = mysql.createConnection({
          host     : '<myserver>',
          user     : '<myusername>',
          password : '<mypw>',
          database : '<mydatabase>'
    });
    connection.connect(function(err){
          if(!err) {
                console.log("Database is connected ... nn");
          }
          else {
                console.log("Error connecting database ... nn");
          }
    });

    connection.query("INSERT INTO Users (user_id) VALUES ('TESTNAME')");
    connection.end();
};
