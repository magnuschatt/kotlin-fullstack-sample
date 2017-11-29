var serverFactory = require('spa-server');

var server = serverFactory.create({
  path: './build/web',
  port: 80,
  fallback: '/frontend.html'
});

server.start();