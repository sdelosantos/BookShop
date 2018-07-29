const mongo = require("../db/mongo");
const GUI = require("../services/guid.generator")
const bookModel = require("../model/book");
let checkBook = function(book){
    return true;
}
let cloneObject = function(item){
    let newObject = {};
    for(o in item){
        if(typeof(item[o])!="object"|| item[o]==null){
            newObject[o] = item[o];   
        }
        else{
            newObject[o] = cloneObject(item[o]);   
        }
    }                
  return newObject;
  }
  
class BookService{
    constructor(){
        this.listBook = [];
        //this.collection = mongo.schemes.books;
    }

    listBooks(callback){
        const myIP = "192.168.8.101:3000";
        let book = new bookModel();
        book.title = "Jouney to the Centre of the Earth";
        book.author.name = "Jules Verne";//,lastName:"Verne", nationality:"Frech"})
        book.cover = "http://"+myIP+"/book/cover/bkJouneyCentreEart";
        book.description = "A dirty slip of parchment falls from the pages of an ancient manuscript. Deciphered by the indefatigable Otto Liedenbrock, professor of geology, and his reluctant nephew, Axel, the parchment’s coded message is a wild assertion made by a medieval alchemist: Inside a volcano in Iceland is a passageway to the center of the earth. Impossible, says Axel—the temperature of the earth’s core is far too high for any human being to go near it. That is one theory, the professor replies. Two days later, they embark on a journey so fantastic it will alter the very meaning of history"
        book.publisher = " Macmillan Collector's Library";
        book.ISBN = "1509827889";
        book.pages = 300;
        book.format = "HardCover"
        book.shipping.description = "FREE Shipping on orders over $25";
        book.price = 15.00;
      
        this.listBook = [];
        book.bookId = GUI.generate();
        this.listBook.push(book);
      
        let b2 = cloneObject(book);
        b2.bookId = GUI.generate();
        book.title = "20,000 Leagues Under the Sea";
        book.cover="http://"+myIP+"/book/cover/20LeaguesUnderTheSea_";
        book.ISBN = "1509824285";
        book.price = 25.85
        book.description = "Professor Aronnax, his faithful servant, Conseil, and the Canadian harpooner, Ned Land, begin an extremely hazardous voyage to rid the seas of a little-known and terrifying sea monster. However, the 'monster' turns out to be a giant submarine, commanded by the mysterious Captain Nemo, by whom they are soon held captive. So begins not only one of the great adventure classics by Jules Verne, the 'Father of Science Fiction', but also a truly fantastic voyage from the lost city of Atlantis to the South Pole."
        
        this.listBook.push(b2);
      
        let b3 = cloneObject(book);
        b3.bookId = GUI.generate();
        b3.title = "From the earth to the moon";
        b3.cover="http://"+myIP+"/book/cover/FromTheEarthToTheMoon";
        b3.ISBN = "850982885";
        b3.price = 5.35
        b3.description = "When the members of the elite Baltimore Gun Club find themselves lacking any urgent assignments at the close of the Civil War, their president, Impey Barbicane, proposes that they build a gun big enough to launch a rocket to the moon. But when Barbicane’s adversary places a huge wager that the project will fail and a daring volunteer elevates the mission to a 'manned flight, one man’s dream turns into an international space race."

        this.listBook.push(b3);
      
        let b4 = cloneObject(book);
        b4.bookId = GUI.generate();

        b4.bookId = GUI.generate();
        b4.title = "The Odyssey";
        b4.cover="http://"+myIP+"/book/cover/laOdicea";
        b4.ISBN = "889982885";
        b4.price = 5.35
        b4.description = "Generally attributed to the ancient Greek poet Homer, “The Odyssey” is considered one of the most important works of classical antiquity, an epic poem about the events at the end of the Trojan War which is generally thought to have been written near the end of the 8th century BC. The story centers on Odysseus and his ten year journey to reach his home in Ithaca. Because of his long absence, Odysseus is presumed dead, leaving his wife Penelope and son Telemachus to deal with a group of suitors, the Proci, who compete for Penelope’s hand in marriage."
        b4.author.name = "Homero"
        this.listBook.push(b4)

        callback(this.listBook);
        // mongo.open(function(m){
        //     m.collection(this.collection,(col)=>{
        //         col.find({}).toArray((error,data)=>{
        //             callback(data);
        //             mongo.close();
        //         })
        //     });
        // });
    }
    book(id){
        mongo.open(function(m){
            m.collection(this.collection,(col)=>{
                col.find({id:id}).toArray((error,data)=>{
                    callback(data);
                    mongo.close();
                })
            });
        });
    }

    addBook(book,callback){
        if(checkBook(book)){
            mongo.open(function(m){
                m.collection(this.collection,(col)=>{
                    col.insertOne(book,(error,data)=>{
                        callback(data);
                        mongo.close();
                    });
                });
            });
        }
    }
}

module.exports = BookService;