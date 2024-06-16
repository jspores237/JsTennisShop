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
//Lines 111-113 mainscreen.html: created "Buy Now" button with POST request to "/buyProduct" URL when "Buy Now" is clicked. The hidden productID is passed to server (set by Thymeleaf using ${tempProduct.id}).
//BuyNowController.java: Includes logic to update inventory (ex: decrement if Buy Now is clicked) and return either the /purchaseSuccess or /purchaseError URL files depending on the result of clicking "Buy Now" (files not created until next comment). This file also included methods to return a message to the user depending on the result.
//purchaseError.html: purchase unsuccessful html template.
//purchaseSuccess.html: purchase successful html template.

G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.
//Lines 82-92 Part.java: Getters and setters for MinInv and MavInv (both set with 0 as minimum)
//Lines 38-51 Part.java: Constructors with minInv and maxInv added.
//BootStrapData.java: Adds setters for minInv and maxInv for each part (in the part initialization).
//Lines 26 & 27 OutsourcedPartForm.html: Allows users to specify min and max inventory levels for parts.
//Lines 26 & 27 InhousePartForm.html: Allows users to specify min and max inventory levels for parts.
//Changed database file name to J'sTennisShop.db. Went to application.properties and changed database name accordingly.
//Lines 100 & 101 Parts.java: Method to ensure inventory levels for parts are between min and max.
//InhousePartController.java and OutsourcedPartController.java created. Controller maps and handles POST requests for creating and updating InhousePart objects. Validates inventory is between min and max values. Uses BindingResult to manage validation errors (outputs message to user if bad input). Saves valid InhousePart objects to database.
//Line 54 & 55 InhousePartServiceImpl.java (Note for attempt 2): Added logic for ensuring inventory is between min and max.
//Lines 37 & 38 AddInhousePartController.java (Note for attempt 2): Added check to see if inventory is between min and max before saving. If inventory is not withing range, error message displayed from BindingResult.
//Line 18, 24, 30, & 34 InhousePartForm.html (Not for attempt 2): 
//Line 15 InhousePart.java (Note for attempt 2): Changes id to a private field.
//Lines 17, 20, 23, 26, 29 & 32 InhousePartForm.html (Note for attempt 2): Outputs correct error message to user.
//Lines 19, 22, 25, 28, 31 & 34 OutsourcedPartForm.html (Note for attempt 2): Outputs correct error message to user.
//Lines 45-47 AddProductController.java (Note for attempt 2): added try, catch blocks 
//Added a ConfirmationController.java to handle the part and product confirmation pages

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.
//Lines 25-29 InhousePartController.java and OutsourcedPartController.java: Additional error messages added if inventory is out of min-max zone.
//Lines 33-46 EnufPartsValidator.java: for loop to iterate through and return the set of parts associated with a product. The inventory collection is checked to see if inventory decrementing by one would cause it to fall below the minimum. Default constraint violation message disabled (updated with more specific warning message about adding another product).
//Product.java and Part.java: Modified inventory setter methods to ensure inv value is between the minInv and maxInv values (also that the values are not set to 0, which they kept being changed to in my last submission).


I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.
//Lines 104-137 PartTest.java: added getters and setters for minInv and maxInv.
J.  Remove the class files for any unused validators in order to clean your code.
//DeletePartValidator.java deleted due to not being used.