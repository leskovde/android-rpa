# Examples

These examples can be found in `wawinterpreter.inputs.ExampleInputs`.

## Simple Workflow

```
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
```

This workflow opens the app drawer and idles for 1 second.
It tests that:
- Metadata is parsed and used correctly
- Description selector works
- ResourceId selector works
- Device actions work
- $before and $after work
- Empty selectors work (and do not match anything by default)

## Extract Text

```
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
```

This workflow opens the app drawer, opens the settings, opens the battery settings, gets the battery level and locks the screen.
It tests that:
- Description selector works
- ResourceId selector works
- Text selector works
- $before and $after work
- Device actions work
- Variable assignment works
- Empty selectors work (and do not match anything by default)
- Text extraction works 