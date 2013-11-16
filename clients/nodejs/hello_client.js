var thrift = require('thrift')
  , ttypes = require('./gen-nodejs/hello_types')
  , HelloService = require('./gen-nodejs/HelloService.js');

var connection = thrift.createConnection('localhost', 8001);
//var connection = thrift.createConnection('localhost', 8081, {transport: thrift.TFramedTransport});
var client = thrift.createClient(HelloService, connection);

connection.on('error', function(err) {
  console.error(err);
});

var msg = new ttypes.HelloMsg({ name: 'from nodejs' });

client.sayHello(msg, function(err, response) {
  if (err) {
    console.error(err);
    process.exit(1);
  } else {
    console.log(response.name);
    process.exit(0);
  }
  connection.end();
});