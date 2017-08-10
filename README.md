# OFFER TRACKER
A Home Offer Tracker (Developed at Inman Hackathon)
[![Offer Tracker Demo](http://i.imgur.com/qnfo94Y.gif)](https://youtu.be/t9ycYMwvHQQ)

## Idea: 
 - **Bradley Inman**
 - **Shane Farkas** (*The Agency*) shane@theagencyre.com
 - **Joel Burslem** (*1000watt*)
 - **Paul Cooley** (*Imprev*) paul@imprev.com
 - **Tom Flanagan** (*The Group Inc*)


## Developers: 
 - **Artur Grigio** ([*ListingZen*](https://ListingZen.com)): arturgrigio@gmail.com
 - **Todd LaMothe** ([*Union Street Media*](https://unionstreetmedia.com)): todd@unionstreetmedia.com
 - **Ami Berger** ([*Voiceter Pro*](http://www.voiceterpro.com)): ami@voiceterpro.com
 - **Tomas McCandless** ([*Voiceter Pro*](http://www.voiceterpro.com)): tomas@voiceterpro.com
 - **Ricky Watts** ([Coldwell Banker Heritage](http://www.coldwellbankerishome.com)): ricky.watts@coldwellbanker.com


## Stack:
| **API**                  | **DB**  | **FRONTEND** | **APP**      |
| ------------------------ | ------- | ------------ | ------------ |
| AWS Lambda + API Gateway | AWS RDS | Vue.js       | React Native |
 
 
# API DOCUMENTATION

### Offers
**GET**	 - `api/agents/:agent_id/offers`

**GET**	 - `api/buyers/:buyer_id/offers`

**GET**	 - `api/sellers/:seller_id/offers`

**GET**	 - `api/offers/search`

*`payload`*
````
"{
    ""buyer_id"": """",
    ""seller_id"": """",
    ""agent_id"": """"
}"
````
    
*`response`*

````
"[
    {
        ""address"": ""123 Main Street"",
        ""mls_number"": ""55454454"",
        ""agent_first_name"": ""Jane"",
        ""agent_last_name"": ""Doe"",
        ""agent_brokerage"": ""XYZ Brokerage"",
        ""agent_designations"": ""CPRS, e-Pro"",
        """": """"
    },
    {
        ""address"": ""456 Elm Street"",
        ""mls_number"": ""098767890"",
        ""agent_first_name"": ""Jane"",
        ""agent_last_name"": ""Doe"",
        ""agent_brokerage"": ""XYZ Brokerage"",
        ""agent_designations"": ""CPRS, e-Pro"",
        """": """"
    },
    {
        ""address"": ""122 Andrews Ave"",
        ""mls_number"": ""2465422332"",
        ""agent_first_name"": ""Jane"",
        ""agent_last_name"": ""Doe"",
        ""agent_brokerage"": ""XYZ Brokerage"",
        ""agent_designations"": ""CPRS, e-Pro"",
        """": """"
    }
]"
````
    
**GET**	 - `api/offers/:token`

**PUT**	 - `api/offers/:offer_Id/events`

*`payload`*
````
"{
   ""action"": ""next/prev"",
   ""event_id"": """"
}"
````

**POST**	 - `api/offers/:offerID/subscribe`

*`payload`*
````
"{
   ""phone"": """",
   ""first_name"": """",
   ""last_name"": """"
}"
````
	
### Agents
**PUT**	 - `api/agents`

*`payload`*
````
"[
  {
    ""first_name"": """",
    ""last_name"": """",
    ""photo_url"": """",
    ""cell"": """",
    ""email"": """",
    ""brokerage_name"": """",
    ""designations"": """",
    ""session_token"": """"
  }
]"
````
**POST**	 - `api/agents/create`

*`payload`*
````
" {
    ""first_name"": """",
    ""last_name"": """",
    ""photo_url"": """",
    ""cell"": """",
    ""email"": """",
    ""brokerage_name"": """",
    ""designations"": """",
    ""password"": """",
    ""session_token"": """"
}"
````

**POST**	 - `api/agents/login`

*`payload`*
````
" {
    ""email"": """",
    ""password"": """",
    ""session_token"": """"
}"
````
	
### Events
**GET**	 - `api/events`

**GET**	 - `api/events/:event_id`
