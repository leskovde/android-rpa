package com.rpa.wawinterpreter.inputs;

/*
where:
    - id ($after, $before)
    - selectors
        - index
        - resId
        - className
        - desc
        - text
what:
    - action:
        - click
        - longClick
        - swipe
        - idle
        - getText
        - getValue
        - getImage
        - setText
        - setValue
    - params:
        - position
        - text
        - value
        - swipe direction
        - selector

 */

public class ExampleInputs {
    public static final String SIMPLE_WAW = """
            {
            	"meta" : {
                    "name": "Google Maps Scraper",
                    "desc": "A blazing fast scraper for Google Maps search results."
                }
            	"workflow": [
            		{
            			"id": "login",
            			"where": {
            			    "$after": "signup",
            			    "selectors": [
            			        { "index" : "0" },
            			    ]
            			},
            			"what": [
            			        {
                                    "action": "idle",
                                    "args": [
                                        1000
                                    ]
                                }
            			]
            		},
            		{
            			"id": "signup",
            			"where": {...},
            			"what": [...]
            		},
            		...
            	]
            ]
            """;
}
