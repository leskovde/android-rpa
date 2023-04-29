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