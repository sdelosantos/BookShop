var express = require('express');
const service = require("../services/implement");
let user = require("../model/user")
var router = express.Router();

/* GET users listing. */
router.get('/ind', function(req, res, next) {
  res.send('respond with a resource');
});
router.post('/login', function(req, res, next) {
  let us = new user();
  us.email = req.body.email;
  us.password = req.body.password;
  console.log(us);
  service.UserService.login(us, (log,user)=>{
    console.log(log);
    if(log){
      res.send(user);
    }else{
      res.send({});
    }
  });
});
module.exports = router;
