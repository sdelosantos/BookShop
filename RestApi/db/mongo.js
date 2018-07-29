let mongo = require("mongodb").MongoClient;
let set = require("./schemes");
const database = set.mongo.database;
const url = set.mongo.url;

class mongoElements{
    constructor(){
        this.connect = {}
        this.db = {}
        this.schemes = set.mongo.collection
    }
    open(callback){
        if(typeof(callback)=="function"){
            mongo.connect(url,{useNewUrlParser: true},(error,con)=>{
                if(error){
                    console.log(error)
                    return false;
                }
                this.connect = con;
                this.db = con.db(database);
                callback(this);
            });
        }
    }
    // LOAD OR CREATE COLLECTION
    collection(collName,callback){
        if(typeof(callback)=="function"){
            callback(this.db.collection(collName));
        }
    }
    close(){
        this.connect.close();
    }
}

module.exports = new mongoElements();