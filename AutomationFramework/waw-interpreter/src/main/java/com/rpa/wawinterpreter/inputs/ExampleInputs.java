package com.rpa.wawinterpreter.inputs;

/*
where:
    - id ($after, $before)
    - selectors
        - type (required)
            - button
            - calendarview
            - checkbox
            - imageview
            - progressbar
            - searchview
            - textedit
            - textview
            - webview
        - index
        - resId
        - className
        - desc
        - text
what:
    - action:
        - click
            - selector
        - longClick
            - selector
        - swipe
            - fromPosition
            - toPosition
        - idle
            - duration (int)
        - getText
            - selector
            - variable
        - getValue
            - selector
            - variable
        - getImage
            - selector
            - path
        - setText
            - selector
            - text
            - variable
        - setValue
            - selector
            - value (boolean)
        - home
        - back
        - recentApps
        - volumeUp
        - volumeDown
        - lockScreen
        - unlockScreen
        - openNotification
        - openAppDrawer
    - params:
        - text
        - value
        - selector

 */

// TODO: Implement the device-dependent actions

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
            			         {
            			            "type" : "textedit",
            			            "index" : "0"
                                 },
            			    ]
            			},
            			"what": [
            			        {
                                    "action": "idle",
                                    "args": {
                                        "duration": 1000
                                    }
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
