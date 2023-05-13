# Workflow

1. Define the control
    - Use the controls constructor to set the relevant search properties
    - e.g. `textEdit = new TextEdit() { text = "Hello World" }`

2. Find the control
    - Use the `tryFind()` method to find the control
        - Matches all (or some) of the search properties
    - e.g. `textEdit.tryFind()`
    - Use a specific search method:
        - `textEdit.tryFindByDescription()`

3. Interact with the control
    - `click()`, ...

## Checklist

- [x] Find UiElement by text content
- [x] Find UiElement by resource id
- [x] Find UiElement by description
- [x] Find UiElement by class name
- [x] Find UiElement by index
- [x] Find UiElement by absolute position
- [x] Find UiElement by relative position
- [x] Get text from TextBasedUiElement
- [x] Get checked value from CheckableUiElement
- [x] Get image from ImageBasedUiElement
- [x] Edit text in TextBasedUiElement
- [x] Change checked value in CheckableUiElement
- [x] Click on UiElement
- [x] Long click on UiElement
- [x] Click on absolute position
- [x] Long click on absolute position
- [x] Click on relative position
- [x] Long click on relative position
- [x] Swipe vertically from position
- [x] Swipe horizontally from position
- [x] Press SW buttons (menu, back, home)
- [x] Press HW buttons (volume up, volume down, power)

## Bug fixes

- [x] getClass returns UiObject, getClassName returns the proper class name, but as a string... Rewrite the type compatibility check to use the string.
- [x] Open app drawer