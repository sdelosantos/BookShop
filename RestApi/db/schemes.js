function schemes(){
    // MONGO SETTINGS
    this.mongo = {
        url:"mongodb://mongodb@127.0.0.1:27017",
        database:"BookShopDB",
        collection:{
            user:"UserCollection",
            books:"BookCollection",
            cart:"cartCollection"
        }
    }
}
module.exports = new schemes();