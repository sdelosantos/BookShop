let bookService = require("./book.service");
let userService = require("./user.service");

function Implement(){
    this.BookService = new bookService();
    this.UserService = new userService();
}

module.exports = new Implement();
