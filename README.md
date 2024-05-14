Jonathan Spores README final project (My responses to each section below begin with "//")

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.
//Line 19-49 mainscreen.html: adds css file. Just sets a base font and some basic spacing for the Home and About Screen buttons to help looks.
//Line 13 mainscreen.html: Changed title to "J's Tennis Shop"
//Line 18 mainscreen.html: Got rid of container and centered text. Changed h1 contents to "J's Tennis Shop"
//Line 55 mainscreen.html: Changed h2 contents to "Accessories"
//Line 87 mainscreen.html: Changed h2 contents to "Rackets"
//Line 101 mainscreen.html: Changed Button to say "Add racket"
//Line 18-21 mainscreen.html: Added centered buttons for the home page and the about page

Note: Do not remove any elements that were included in the screen. You may add any additional elements you would like or any images, colors, and styles, although it is not required.

D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.
//aboutPage.html created. I first added in the text (line 48-end) and then added in the css (line 8-47). I also added in a button to return to the home page
//AboutPageController.java created. Set mapping to aboutPage.html then created simple function to return AboutPage.html

E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.
Note: Make sure the sample inventory is added only when both the part and product lists are empty. When adding the sample inventory appropriate for the store, the inventory is stored in a set so duplicate items cannot be added to your products. When duplicate items are added, make a “multi-pack” part.
//Lines 42-108 BootStrapData.java: Initializing parts and products, while saving to database.
//Line 30-37 BootStrapData.java: 'if else' block to ensure inventory counts are at 0 before data is added.
//Line 39 BootStrapData.java: initializeInventory() added with initialization data inside 
//Lines 110-119 BootStrapData.java: addPartIfUnique() method created to ensure no duplicate items (creates a Hash Set, which doesn't allow duplicates.)

F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

J.  Remove the class files for any unused validators in order to clean your code.