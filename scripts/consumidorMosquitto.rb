require 'rubygems'
require 'mqtt'
require 'json'
require 'net/http'
require 'uri'

def create_register(tipo, unidad, valor)
  uri = URI.parse('http://localhost:3000/api/registro_mediciones')
  header = {'Content-Type': 'application/json'}

  payload = {
    "registro_medicion": {
      "valor": valor,
      "tipo": tipo,
      "unidad": unidad
    }
  }

  http = Net::HTTP.new(uri.host, uri.port)
  request = Net::HTTP::Post.new(uri.request_uri, header)
  request.body = payload.to_json

  response = http.request(request)
  puts "#{response.code} #{tipo}"
end

client = MQTT::Client.connect(:host => '172.24.42.32', :port => 8083 )
client.subscribe( 'alta.piso1.local1' )

client.get do |topic,message|
  puts "-----------------------------------------"
  puts topic
  puts "-----------------------------------------"
  json = JSON.parse(message)
  puts json

  create_register("Temperature",json['Temperature']['Unit'], json['Temperature']['Value'])
  create_register("Sound",json['Sound']['Unit'], json['Sound']['Value'])
  create_register("Monoxide",json['Monoxide']['Unit'], json['Monoxide']['Value'])
  create_register("Lux",json['Lux']['Unit'], json['Lux']['Value'])
  # TODO Mandar el place
end

client.disconnect()
