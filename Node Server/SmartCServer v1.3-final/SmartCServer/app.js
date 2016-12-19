
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')
  , user = require('./routes/user')
  , http = require('http')
  , path = require('path');

var myParser = require("body-parser");
var open = require('open');
var sleep = require('system-sleep');

var app = express();


var mysql      = require('mysql');
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'root',
  database : 'redpin'
});

// Connect to the mysql database
connection.connect(function(err){ 
	if(!err) {
	    console.log("Database is connected ... nn");    
	} else {
	    console.log("Error connecting database ... nn");    
	}
	});

	 app.use(myParser.urlencoded({extended : true}));

// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());



app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));


var row = [];
var locationId = 0;
var symbolicId = "";
var mapId =  0;
var mapXCord = 0;
var mapYCord = 0;
var accuracy = 0;

var distanceBT =0;
var distance1 = 0;
var distance2 = 0;
var distance3 = 0;

var node1X=0,
    node2X=0,
    node3X=0,
    node1Y=0,
    node2Y=0,
    node3Y=0;
var ap1=0,
    ap2=0,
    ap3=0; 

var blenode1,
    blenode2,
    blenode3;  

var mapnumber=0;    

var r1=0,r2=0,r3=0;
var x1=0,x2=0,
    x3=0,y1=0,
    y2=0,y3=0;

var f,g,x=0,y=0;
var intersectX =0;
var intersectY =0;

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}



/*Recieves the location from the Java Server. Also get the bluetooth coordiantes
 associated with the location id */
app.post('/',function(request,response){  
	
	 console.log(request.body); 
	 console.log("in here");
	  /*Get the details about the location */ 
	    connection.query('SELECT * FROM redpin.location where locationId =' + parseInt(request.body.locationid), function(err, rows, fields) {
	    if (!err){
	    	console.log('The solution is: ', rows[0]);
	    	var data = JSON.stringify(rows);
	    	console.log(JSON.stringify(rows));
	    	console.log(data);
	       
	        var row1 = JSON.parse(data);
	        row = row1[0].mapID;
	        mapId = row1[0].mapId;
	        symbolicId = row1[0].symbolicId;
	        mapXCord = row1[0].mapXCord;
	        mapYCord = row1[0].mapYCord;
	        
	        console.log("roe: "+ row);
	        console.log(row1)
	        console.log(row1[0]);;
	        console.log(row1[0].mapId);
	        
	    }		
	    else
			console.log('Error while performing Query.');
	  });
	    /*Get the bluetooth coordiantes from the location*/
	    connection.query('SELECT * FROM redpin.bletrilaterate where locid =' + /*parseInt(request.body.locationid)*/5, function(err, rows, fields) {
	    	if(!err){
	    		console.log('The solution is: ', rows[0]);
	    		node1X = rows[0].node1X;
	    		node1Y = rows[0].node1Y;
	    		node2X = rows[0].node2X;
	    		node2Y = rows[0].node2Y;
	    		node3X = rows[0].node3X;
	    		node3Y = rows[0].node3Y;
	    		blenode1 = rows[0].node1;
	    		blenode2 = rows[0].node2;
	    		blenode3 = rows[0].node3;
	    	}else{
	    		console.log("error connecting db");
	    	}
	    });
	sleep(5000);
	/*open the alert in new window*/
	open('http://localhost:3000/test1', function (err) {
     if (err) throw err;
        console.log('The user closed the browser');
     });
	console.log("in post"+row);
	console.log(request.body);
	response.send("Success");
});

app.post('/postTest',function(req,res){
	console.log("in postTest");
	res.send("Done");
});

/*Display the alert along with  the location on the webpage*/
app.get('/test1',function(req,res){
	   JSON.stringify(req.body);

        x1 = node1X;y1 = node1Y/*505*/;     
        x2 = node2X;y2 = node2Y /*472*/;
        x3 = node3X /*224*/;y3 = node3Y /*530*/;

        /*scale the distances according to the map size*/
        r1 = distance1*3.575;
        r2 = distance2*3.575;
        r3 = distance3*3.575;

          /*Formula to calculate the x,y coordinates*/ 
         f= ((Math.pow(r1,2)-Math.pow(r2,2))+(Math.pow(x2,2)-Math.pow(x1,2))+(Math.pow(y2,2)-Math.pow(y1,2)))*(2*y3 - 2*y2)-((Math.pow(r2,2)-Math.pow(r3,2))+(Math.pow(x3,2)-Math.pow(x2,2))+(Math.pow(y3,2)-Math.pow(y2,2)))*(2*y2-2*y1);
       	 g=((2*x2-2*x3)*(2*y2-2*y1)-(2*x1-2*x2)*(2*y3-2*y2));
       
     	 x=f/g;     // Formula to calculate the X coordinate

     	 /*Formula to calculate the Y coordinate*/	
     	 y= ((Math.pow(r1,2)-Math.pow(r2,2))+(Math.pow(x2,2)-Math.pow(x1,2))+(Math.pow(y2,2)-Math.pow(y1,2))+ x*(2*x1-2*x2))/ (2*y2-2*y1);
         console.log(x+" "+y+" ");


	   if(ap1==-1 ||ap1==0 ){
	   	console.log("in if render");
	    res.render('indoor', {   
	    	symbolicId : symbolicId,
        	intersectX : mapXCord,
        	intersectY : mapYCord,
        	distance1 : distance1,
        	distance2 : distance2,
        	distance3 : distance3
	    	  }
	    );
	  }else{
	  	var xx = x;
	  	var yy = y;
	  	x=0;y=0;distance1=0;distance2=0;distance3=0;
	  	console.log("in else render");
	  	res.render('indoor', {   

	  		symbolicId : symbolicId,
        	mapXCord : mapXCord,
        	mapYCord : mapYCord,
        	intersectX : xx,
        	intersectY : yy,
        	distance1 : distance1,
        	distance2 : distance2,
        	distance3 : distance3

	    	  }
	    );

	  }
	});

/*Calculate the location through trilateration */
app.get('/test2',function(req,res){
      var d = new Date();
      var n = d.getTime();
      /*Record the start time*/
      console.log("trialte start time: "+ n);
  	    JSON.stringify(req.body);
	    /*distance1 = 10.725;
	    distance2 = 53.625;
	    distance3 = 75.075;*/
     	console.log("node1X: "+node1X);

     	/*Get the coordiantes of the bluetooth nodes*/
        x1 = node1X;y1 = node1Y/*505*/;     
        x2 = node2X;y2 = node2Y /*472*/;
        x3 = node3X /*224*/;y3 = node3Y /*530*/;

        /*scale the distances according to the map size*/
        r1 = distance1*3.575;
        r2 = distance2*3.575;
        r3 = distance3*3.575;

          /*Formula to calculate the x,y coordinates*/ 
         f= ((Math.pow(r1,2)-Math.pow(r2,2))+(Math.pow(x2,2)-Math.pow(x1,2))+(Math.pow(y2,2)-Math.pow(y1,2)))*(2*y3 - 2*y2)-((Math.pow(r2,2)-Math.pow(r3,2))+(Math.pow(x3,2)-Math.pow(x2,2))+(Math.pow(y3,2)-Math.pow(y2,2)))*(2*y2-2*y1);
       	 g=((2*x2-2*x3)*(2*y2-2*y1)-(2*x1-2*x2)*(2*y3-2*y2));
       
     	 x=f/g;     // Formula to calculate the X coordinate

     	 /*Formula to calculate the Y coordinate*/	
     	 y= ((Math.pow(r1,2)-Math.pow(r2,2))+(Math.pow(x2,2)-Math.pow(x1,2))+(Math.pow(y2,2)-Math.pow(y1,2))+ x*(2*x1-2*x2))/ (2*y2-2*y1);
         console.log(x+" "+y+" ");

         console.log("trilaterate end time: "+n);   /*Record the end time*/

         // Display the page along with the coordinates
	    res.render('indoor', {   
	    	distance1 : r1,
	    	distance2 : r2,
	    	distance3 : r3,
	    	symbolicId : symbolicId,
	    	mapXCord : mapXCord,
        	mapYCord : mapYCord,
        	intersectX : x,
        	intersectY : y,
        	mapID : mapId
        	 }
	    );
	});
app.get('/users', user.list);

/*Caculate the distances(distance1, distance2, distance3) of the nodes base don their RSSI values*/
app.post('/test',function(req,res){
	    
	    console.log("in test"+JSON.stringify(req.body));
	    var d = JSON.stringify(req.body);
	    var array = d.split(",");
	    console.log(array);

	    /*Calulate distance1 */
	    ap1 = array.indexOf(" "+blenode1);
	    var node1 = parseInt(Math.abs(array[ap1+2]));
	    console.log(node1);
	    node1 = node1 -10;
	    if(node1>=30 && node1<=39){
	    	distance1 = 0;
	    }else if(node1>=40 && node1<=50 ){
	    	distance1 = 3;
	    }else if(node1>=51 && node1<=55 ){
	    	distance1 = 6;
	    }else if(node1>=56 && node1<=60 ){
	    	distance1 = 9;
	    }else if(node1>=61 && node1<=63 ){
	    	distance1 = 12;
	    }else if(node1>=64 && node1<=66 ){
	    	distance1 = 15;
	    }else if(node1>=67 && node1<=69 ){
	    	distance1 = 18;
	    }else if(node1>=70 && node1<=73 ){
	    	distance1 = 21;
	    }else if(node1>=73 && node1<=76 ){
	    	distance1 = 24;
	    }else if(node1>=76 && node1<=79 ){
	    	distance1 = 27;
	    }else if(node1>=79 && node1<=81 ){
	    	distance1 = 30;
	    }else if(node1>81){
	    	distance1 = 33;
	    }
	    console.log(distance1);

	    /*Calulate distance2 */
	     ap2 = array.indexOf(" "+blenode2);
	    var node2 = parseInt(Math.abs(array[ap2+2]));
	    console.log(node2);
	    node2 = node2 -10;	
	    if(node2>=30 && node2<=39){
	    	distance2 = 0;
	    }else if(node2>=40 && node2<=50 ){
	    	distance2 = 3;
	    }else if(node2>=51 && node2<=55 ){
	    	distance2 = 6;
	    }else if(node2>=56 && node2<=60 ){
	    	distance2 = 9;
	    }else if(node2>=61 && node2<=63 ){
	    	distance2 = 12;
	    }else if(node2>=64 && node2<=66 ){
	    	distance2 = 15;
	    }else if(node2>=67 && node2<=69 ){
	    	distance2 = 18;
	    }else if(node2>=70 && node2<=73 ){
	    	distance2 = 21;
	    }else if(node2>=73 && node2<=76 ){
	    	distance2 = 24;
	    }else if(node2>=76 && node2<=79 ){
	    	distance2 = 27;
	    }else if(node2>=79 && node2<=81 ){
	    	distance2 = 30;
	    }else if(node2>81){
	    	distance2 = 33;
	    }
	    console.log(distance2);

	    /*Calulate distance3 */
        ap3 = array.indexOf(" "+blenode3);
	    var node3 = parseInt(Math.abs(array[ap3+2]));
	    console.log(node3);
	    node3 = node3-10;
	    if(node3>=30 && node3<=39){
	    	distance3 = 0;
	    }else if(node3>=40 && node3<=50 ){
	    	distance3 = 3;
	    }else if(node3>=51 && node3<=55 ){
	    	distance3 = 6;
	    }else if(node3>=56 && node3<=60 ){
	    	distance3 = 9;
	    }else if(node3>=61 && node3<=63 ){
	    	distance3 = 12;
	    }else if(node3>=64 && node3<=66 ){
	    	distance3 = 15;
	    }else if(node3>=67 && node3<=69 ){
	    	distance3 = 18;
	    }else if(node3>=70 && node3<=73 ){
	    	distance3 = 21;
	    }else if(node3>=73 && node3<=76 ){
	    	distance3 = 24;
	    }else if(node3>=76 && node3<=79 ){
	    	distance3 = 27;
	    }else if(node3>=79 && node3<=81 ){
	    	distance3 = 30;
	    }else if(node3>81){
	    	distance3 = 33;
	    }
	    console.log(distance3);	    
	    
		/*Send response to request*/
	  res.send("ble sent Success");
	  
	});

//Create the server 
http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
})
