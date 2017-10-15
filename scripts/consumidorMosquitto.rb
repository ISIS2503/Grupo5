require 'rubygems'
require 'mqtt'
require 'json'
require 'net/http'
require 'uri'

def create_register(tipo, unidad, valor)
  uri = URI.parse('http://localhost:3000/api/registro_mediciones')
  header = {'Content-Type': 'text/json'}

  payload = {
    "registro_medicion": {
      "tipo": tipo,
      "unidad": unidad,
      "valor": valor
    }
  }

  http = Net::HTTP.new(uri.host, uri.port)
  request = Net::HTTP::Post.new(uri.request_uri, header)
  request.body = payload.to_json

  response = http.request(request)
end

client = MQTT::Client.connect(:host => '172.24.42.32', :port => 8083 )
client.subscribe( 'alta.piso1.local1' )

client.get do |topic,message|
  puts "-----------------------------------------"
  puts topic
  puts "-----------------------------------------"
  json = JSON.parse(message)
  puts json

  create_register("","","")
end

client.disconnect()
