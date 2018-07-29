var author = require("./author")
var shipping = require("./shipping")

class Book{
    constructor(){
        this.bookId = null;
        this.ISBN = null;
        this.title = null;
        this.cover = null;
        this.author = new author();
        this.pusblisher = null;
        this.description = null;
        this.pages = 0;
        this.format = null;
        this.language = null
        this.price = 0;
        this.shipping = new shipping();
    }
}
module.exports = Book;