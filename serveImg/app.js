var express = require('express');
var app = express();
var cors = require('cors');
//setting middleware
app.use(cors());
app.use(express.static('public')); //Serves resources from public folder



app.listen(5000);