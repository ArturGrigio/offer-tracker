'use strict';

/*
    Return offer search results that meet the specified criteria
*/
exports.handler = (event, context, callback) => {
    console.log('Received event:', JSON.stringify(event, null, 2));

    callback(null, {
        body: { "message" : "success!"}
    });


};
