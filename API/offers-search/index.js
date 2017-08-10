'use strict';

var AWS = require("aws-sdk");
var mysql = require('mysql');

exports.handler = (event, context, callback) => {
    console.log("Querying database...");
    var result = queryDatabase(function(result) {
        console.log("about to return result 2:");
        console.log(result);
        callback(null, result);
    });
};

function queryDatabase(callback) {
    var connection = mysql.createConnection({
          host     : 'tracker.chqgb39str0o.us-west-1.rds.amazonaws.com',
          user     : 'tracker',
          password : 't824>8vRfG)HKn',
          database : 'tracker'
    });

    connection.connect(function(err){
          if(!err) {
                console.log("Database is connected");
                connection.query(`
                  select
                  	offers.address,
                      offers .mls_number,
                      agents.first_name "agent_first_name",
                      agents.last_name "agent_last_name",
                      agents.brokerage "agent_brokerage",
                      agents.designations "agent_designations"
                  from
                  	offers
                      join agent_offer on offers.id = agent_offer.offer_id
                      join agents on agent_offer.agent_id = agents.id
                  `, function (err, result, fields) {
                    connection.end();
                    if (err) {
                        console.log("An error occured:");
                        console.log(err);
                    };
                    console.log("about to return result 1:");
                    console.log(result);
                    callback(result);
                  })
          }
          else {
                console.log("Error connecting database ... nn");
                console.log(err);
          }
    });

};
