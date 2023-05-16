package com.rpa.wawinterpreter.inputs;

/*
where:
    - id ($after, $before)
    - selectors
        - type (required)
            - button
            - calendar_view
            - check_box
            - image_view
            - progress_bar
            - search_view
            - text_edit
            - text_view
            - web_view
            - view
        - index
        - res_id
        - class_name
        - desc
        - text
what:
    - action:
        - click
            - selector
        - long_click
            - selector
        - swipe
            - fromPosition (selector)
            - toPosition (selector)
        - idle
            - duration (int)
        - get_text
            - selector
            - variable
        - get_value
            - selector
            - variable
        - get_image
            - selector
            - path (string)
        - set_text
            - selector
            - text (string)
            - variable
        - set_value
            - selector
            - value (boolean)
        - home
        - back
        - recent_apps
        - volume_up
        - volume_down
        - lock_screen
        - unlock_screen
        - open_notifications
        - open_app_drawer
    - params:
        - text
        - value
        - selector

 */

public class ExampleInputs {
    public static final String OPEN_AND_IDLE = """
            {
                "meta": {
                    "name": "App Drawer Opener",
                    "desc": "Opens the app drawer and idles."
                },
                "workflow": [
                    {
                        "id": "home",
                        "where": {
                            "selectors": [
                                {
                                    "type": "view",
                                    "desc": "Home"
                                },
                                {
                                    "type": "text_view",
                                    "res_id": "com.google.android.apps.nexuslauncher:id/clock"
                                }
                            ]
                        },
                        "what": [
                            {
                                "action": "open_app_drawer"
                            }
                        ]
                    },
                    {
                        "id": "idle",
                        "where": {
                            "$after": "home",
                            "selectors": []
                        },
                        "what": [
                            {
                                "action": "idle",
                                "args": {
                                    "duration": 1000
                                }
                            }
                        ]
                    }
                ]
            }
            """;

    public static final String EXTRACT_TEXT = """
            {
                "meta": {
                    "name": "Text Extractor",
                    "desc": "Gets the current battery level."
                },
                "workflow": [
                    {
                        "id": "home",
                        "where": {
                            "selectors": [
                                {
                                    "type": "view",
                                    "desc": "Home"
                                },
                                {
                                    "type": "text_view",
                                    "res_id": "com.google.android.apps.nexuslauncher:id/clock"
                                }
                            ]
                        },
                        "what": [
                            {
                                "action": "open_app_drawer"
                            }
                        ]
                    },
                    {
                        "id": "open_settings",
                        "where": {
                            "$after": "home",
                            "selectors": []
                        },
                        "what": [
                            {
                                "action": "click",
                                "args": {
                                    "selectors": [
                                        {
                                            "type": "button",
                                            "desc": "Settings"
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "id": "open_battery",
                        "where": {
                            "selectors": [
                                {
                                    "type": "button",
                                    "text": "Battery"
                                }
                            ]
                        },
                        "what": [
                            {
                                "action": "click",
                                "args": {
                                    "selectors": [
                                        {
                                            "type": "button",
                                            "text": "Battery"
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "id": "get_battery_level",
                        "where": {
                            "selectors": [
                                {
                                    "type": "view",
                                    "desc": "Battery"
                                }
                            ]
                        },
                        "what": [
                            {
                                "action": "get_text",
                                "args": {
                                    "variable": "battery",
                                    "selectors": [
                                        {
                                            "type": "text_view",
                                            "res_id": "com.android.settings:id/usage_summary"
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "id": "hide_battery",
                        "where": {
                            "$after": "get_battery_level",
                            "selectors": []
                        },
                        "what": [
                            {
                                "action": "lock_screen"
                            }
                        ]
                    }
                ]
            }
            """;
}
