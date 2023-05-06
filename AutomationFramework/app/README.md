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

- [ ] Find UiElement by text content
- [ ] Find UiElement by resource id
- [ ] Find UiElement by description
- [x] Find UiElement by class name
- [x] Find UiElement by index
- [ ] Find UiElement by absolute position
- [ ] Find UiElement by relative position
- [ ] Get text from TextBasedUiElement
- [ ] Get checked value from CheckableUiElement
- [ ] Get image from ImageBasedUiElement
- [ ] Edit text in TextBasedUiElement
- [ ] Change checked value in CheckableUiElement
- [ ] Click on UiElement
- [ ] Long click on UiElement
- [ ] Click on absolute position
- [ ] Long click on absolute position
- [ ] Click on relative position
- [ ] Long click on relative position
- [ ] Swipe vertically from position
- [ ] Swipe horizontally from position
- [x] Press SW buttons (menu, back, home)
- [x] Press HW buttons (volume up, volume down, power)