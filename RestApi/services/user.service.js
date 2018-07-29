var user = require("../model/user");
let GUID = require("./guid.generator")
let  listUser= [];
class UserService{
    constructor(){
        let us = new user();
        us.userId = GUID.generate();
        us.userName = "Administrator"
        us.email="admin@test.com";
        us.password = "abc123";
        listUser.push(us);
    }
    login(user,callback){
        let log = false;
        for(let i =0; i<listUser.length;i++){
            let us = listUser[i];
            if(us.email==user.email && us.password==user.password) {
                log = true;
                user = us;
                break;
            }
        }
        console.log(log);
        callback(log,user);
    }
}
module.exports = UserService;