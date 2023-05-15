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
            - fromPosition
            - toPosition
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
            - path
        - set_text
            - selector
            - text
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
        - open_notification
        - open_app_drawer
    - params:
        - text
        - value
        - selector

 */

public class ExampleInputs {
    public static final String SIMPLE_WAW = """
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
}
