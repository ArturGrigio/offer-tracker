'use strict';

var AWS = require("aws-sdk");
var mysql = require('mysql');

const mock_payload = [
  {
    "address": "123 Main Street",
    "mls_number": "55454454",
    "agent_first_name": "Jane",
    "agent_last_name": "Doe",
    "agent_brokerage": "XYZ Brokerage",
    "agent_designations": "CPRS, e-Pro",
    "": "",
    "": "",
    "": "",
    "": "",
  },
  {
    "address": "456 Elm Street",
    "mls_number": "098767890",
    "agent_first_name": "Jane",
    "agent_last_name": "Doe",
    "agent_brokerage": "XYZ Brokerage",
    "agent_designations": "CPRS, e-Pro",
    "": "",
    "": "",
    "": "",
    "": "",
  },
  {
    "address": "122 Andrews Ave",
    "mls_number": "2465422332",
    "agent_first_name": "Jane",
    "agent_last_name": "Doe",
    "agent_brokerage": "XYZ Brokerage",
    "agent_designations": "CPRS, e-Pro",
    "": "",
    "": "",
    "": "",
    "": "",
  }
]


/*
    Return offer search results that meet the specified criteria
*/
exports.handler = (event, context, callback) => {
    console.log('Received event:', JSON.stringify(event, null, 2));
    queryDatabase();
    callback(null, mock_payload);

};

function queryDatabase() {
    var connection = mysql.createConnection({
          host     : 'chqgb39str0o.us-west-1.rds.amazonaws.com:3306',
          user     : '<myusername>',
          password : '<mypw>',
          database : 'tracker'
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
