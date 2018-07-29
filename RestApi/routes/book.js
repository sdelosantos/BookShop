var express = require('express');
var router = express.Router();
const service = require("../services/implement");
router.get('/', function(req, res, next) {
    res.send({hello:"Word"});
});
// GET BOOK LIST
router.get('/list', function(req, res, next) {
   service.BookService.listBooks((list)=>{
     res.send(list)
   });
  res.send(listBook)
});

// READ COVER IMAGE 
router.get("/cover/:imagName",(req,res,next)=>{
  let image = req.params.imagName;
  let path = `/public/images/${image}.jpg`
  res.sendFile(path,{ root : global.pathGlob},function(){
    console.log(arguments);
  });
})
module.exports = router;
