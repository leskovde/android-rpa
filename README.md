# Android RPA Framework

The project enables user to automate Android processes by either writing Android tests that utilize the *automation-framework* module or by writing workflow declarations in the [WAW](https://github.com/apify/waw-file-specification) format.

## Project overview

The project consists of two main modules.

### automation-framework

As a wrapper to the Google's [UiAutomator](https://developer.android.com/training/testing/ui-automator) framework, the module provides a set of classes and methods that enable the user to write Android tests in Java.
The UiAutomator comes with a tool (`uiautomatorviewer`) that enables the user to inspect the UI hierarchy of the device and to find the UI controls that they want to interact with.
This tool provides great insight into the UI hierarchy and can be useful when interacting with this project.

The module is used by the `waw-interpreter` module.

When interacting with the `automation-framework`, the user has access to the following classes:

- `Device` - provides methods for interacting with the device
- `UiElement` and its class hierarchy - provides methods for interacting with UI elements

#### Device

The `Device` class is a singleton and can be accessed by calling the `Device.getInstance()` method. 
The `Device` class provides methods for interacting with the device, such as pressing hardware buttons, clicking on the screen, etc.
The underlying implementation of the `Device` class is the `UiDevice` class from the UiAutomator framework and all actions are performed by calling the corresponding methods of the `UiDevice` class.

##### Possible device interactions

Below is a list of provided methods that change the state of the device.
A more detailed description can be found in the JavaDoc of the `Device` class.

- `pressBack` - presses the back button, the user can specify the number of times the button should be pressed (until successful)
- `pressHome` - presses the home button, the user can specify the number of times the button should be pressed (until successful)
- `pressRecentApps` - presses the recent applications button, the user can specify the number of times the button should be pressed (until successful)
- `pressPower` - presses the power button shortly, locking/unlocking the screen
- `lockScreen` - locks the screen, if the screen is already locked, the method does nothing
- `unlockScreen` - unlocks the screen, if the screen is already unlocked, the method does nothing
- `getDisplayWidth` - returns the width of the device's display in pixels
- `getDisplayHeight` - returns the height of the device's display in pixels
- `click` - clicks on the screen at the specified coordinates, either absolute or relative to the device's display
- `longClick` - performs a long click (0.5 seconds) similarly to simple click
- `swipe` - swipes from one point to another, either absolute or relative to the device's display
- `idle` - puts the device to idle state for the specified amount of time
- `takeScreenshot` - takes a screenshot of the device's display and saves it to the specified file
- `openAppDrawer` - opens the application drawer (in the Nexus launcher)
- `openNotifications` - opens the notifications drawer

#### UiElement

The `UiElement` class is a wrapper to the `UiObject2` class from the UiAutomator framework. It provides methods for interacting with UI elements, such as clicking on the element, getting the element's text, etc. The `UiElement` class is a base class for the following classes:
- `Button`
- `CalendarView`
- `CheckBox`
- `ImageView`
- `ProgressBar`
- `SearchView`
- `TextEdit`
- `TextView`
- `View`
- `WebView`

These are the basic UI elements that we currently support. The type information is used to find the corresponding underlying UI controls and to provide the user with the corresponding methods for interacting with the elements.

Inside a single `UiElement`, there can be multiple underlying UI controls. These can have different interfaces and different capabilities. Currently, the `UiElement` class provides methods for interacting with UiAutomator's `UiObject` and `UiObject2`, but it could support custom UI controls from OCR-based frameworks in the future. 

The different interfaces of the underlying objects are unified using `Assigner`, `Finder`, and `Executor` specializations. For each underlying type, we can specify an `Assigner`, whose job is to verify found UI objects and change the state of the `UiElement` to seamlessly work with the underlying object's interface. The underlying controls can be found by providing `Finder` instances that are responsible of fetching a single underlying type through various means (by index, by resource id, by text, ...). Interacting with the underlying object is done through unified `Executor` interface, in which we run common actions on the found elements.

This way, contributors can easily add support for new UI controls by implementing the `Assigner`, `Finder`, and `Executor` interfaces.

When trying to find and interact with an UI control, the user should do the following:
1. Create a new `UiElement` instance based on the type of the UI control:
    ```java
    Button button = new Button();
    ```

2. Locate the control based on the preferred method:
    ```java
    boolean success = button.tryFindByContent("Settings");
    ```

3. Interact with the control:
    ```java
    button.click();
    ```

The user can verify whether the control has been found successfully by checking the return value of `tryFind...` methods.
Interacting with controls that have not been found will fail, unless a different `Executor` implementation is provided.

##### Possible ways of locating UI elements

Currently, the `Finder` interface requires the following methods to be implemented:
- `findByControlIndex` - finds the UI control by its index (position in the UI hierarchy)
- `findByResourceId` - finds the UI control by its resource id (in full)
- `findByClassName` - finds the UI control by its class name, or the suffix of the class name
- `findByDescription` - finds the UI control by its accessibility description
- `findByTextContent` - finds the UI control by its text content
- `findByPosition` - finds the UI control by its position on the screen (absolute or relative to the device's display)

These methods are then used in the `UiElement` class to find the underlying UI controls.
The base class does not use all of these methods, some are reserved to specific subclasses. 
For example, the `TextView` and other text-based classes uses the `findByTextContent` method.

### waw-interpreter

The added value of this project is the `waw-interpreter` module, which enables the user to write tests in a simple declarative format that is then interpreted and executed on the device.
This turns a single Android test into a fully-fledged interpreter that can be parametrized and reused in different scenarios.

The `waw-interpreter` is an Android application that can be run on any API 30 and higher device.
In the future, we want to move from the Android application to a more convenient project type (e.g., test project).

#### WAW format

The Web Automation Workflow format is described in detail here: [WAW](https://github.com/apify/waw-file-specification).

For the purpose of this MVC, we have changed and simplified parts of the format for easier parsing and stricter validation.

```json
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

The file is a JSON object with two keys: `meta` and `workflow`.
The `meta` key contains metadata about the workflow, such as its name and description.
The `workflow` key contains an array of steps that specify a `where` and `what` clauses.
Based on the current state of the device, we want to pick a workflow step and execute its actions.

The `where` clause specifies the conditions that need to be met for the step to be executed.
These conditions are specified using selectors that are used to find the UI elements on the device.
We go though the steps in order and pick the first one that matches the current state of the device.
Matching means successfully finding all the UI elements specified by the selectors. 

The `what` clause specifies the actions that should be executed when the step is picked.
Some actions are device-specific and do not require any arguments, while others can be parametrized.
One of the way to parametrize the actions is by providing selectors that are used to find the UI elements on which the actions should be executed.

The example above shows a simple workflow that opens the app drawer and idles for 1 second.

WAW supports some more advanced features, such as prerequisites, postrequisites, and variables.
This allows us run an action immediately after a different action has finished and to store the results of scraping operations, such as `get_text`.

##### Selectors

```json
{
    "type": "text_view",
    "res_id": "com.google.android.apps.nexuslauncher:id/clock"
}
```

A selector is a JSON object with the following keys:
- `type` - the type of the UI element. The following types are supported:
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
- selector type and its value, the following are supported:
    - index
    - res_id
    - class_name
    - desc
    - text

##### Where clause

A valid `where` clause is an object with the following keys:
- `$before` - (optional) specifies that this step should be executed before the step with the given id
- `$after` - (optional) specifies that this step should be executed after the step with the given id
- `selectors` - this field is required if we do not specify `$before` or `$after` keys. It is an array of selectors that need to be found on the device for the step to be executed.

##### What clause

```json
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
```

The what clause is an array of actions.
Each action is a JSON object with the following keys:
- `action` - the name of the action to be executed. We support two types of actions:
    - Control-specific actions that should be parametrized with selectors:
        - click
            - selector
        - long_click
            - selector
        - swipe
            - fromPosition (selector)
            - toPosition (selector)
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
    - Device-specific actions that do not require arguments:
        - idle (requires a duration argument, where the value is in milliseconds and is an integer)
        - home
        - back
        - recent_apps
        - volume_up
        - volume_down
        - lock_screen
        - unlock_screen
        - open_notifications
        - open_app_drawer
- `args` if the arguments are required for a given action

#### WAW interpreter

An infrastructure for running WAW files on Android devices was created as a part of this project.
Given a WAW file, the interpreter parses it and executes the workflow on the device.

The interpreter is an Android application that can be run on any API 30 and higher device.
Due to this fact, all inputs have to be provided via the device (likely through resources).
Moreover, extracted data has to be stored on the device as well (e.g., in a file).
Extracted text data can be simply printed to the logcat at the end of the workflow.
This is done for all used variables. Extracted images are stored in the selected path and are accessible using Android's file manager.
There surely exists a way of providing and extracting data from and to the host machine, but I found it to be out of scope for this project.

The interpreter is located in the `AutomationRunner` class.
This class contains a single test that parses the WAW file and executes the workflow.
After running the test, all used variables are printed to the logcat.

The parsing is done recursively by constructing a `MappedWaw` instance.
The `MappedWaw` type represents the WAW file - it contains both the metadata and the workflow.
The workflow can be executed by running `run` on the `MappedWaw` instance.

Running the workflow is done by first getting the initial state, which is currently done by waking the device up and going to the home screen.
We then let the workflow execute actions for the provided state and once the actions are finished, we get the new state.
This is done by going over all `where` clauses and finding the first one (in declaration order) that matches the current state.
If no such clause is found, we terminate the interpreter.

Running actions on the given state could be implemented in multiple ways.
Currently, we only run a single `what` clause per matched `where` clause.
We run the first one that was declared, to be precise.

## Running the project

Using the provided Gradle scripts, one can build the individual modules and run the them on the device.
There is one additional module for a simple app that can be used for testing.
This application in `ui-element-showcase` features all basic UI elements in a single scrollview and can be used to test the selectors.

### Prerequisites

The project has been tested on the following configuration, but it should support any API 30 and higher device.

- Pixel 4 API 31 emulator (Android 12.0 Google Play)
- Android Studio 2022.2

### Running automation-framework

Since the `automation-framework` is a library, it does not occupy any resources on the device.
It can be used by other modules by adding it as a dependency.
It can also be used by tests.
One such test is the `ShowcaseTest` in the `com.rpa.automationframework` package.
This test will do perform basic actions on the device as well as extract an image and text from the `ui-element-showcase` application.

### Running waw-interpreter

The `waw-interpreter` module contains the interpreter application.
It can be run on the device by running the `AutomationRunner` test.
Due to its dependency on the `automation-framework` module, it requires building the `automation-framework` module first.