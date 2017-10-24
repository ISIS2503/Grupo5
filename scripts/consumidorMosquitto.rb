require 'rubygems'
require 'mqtt'
require 'json'
require 'net/http'
require 'uri'

def create_register(valor, promedio, nivel, area)
  uri = URI.parse('http://localhost:3000/api/registro_mediciones')
  header = {'Content-Type': 'application/json'}

  payload = {
    "registro_medicion": {
      "valor": valor
    },
    "promedio": promedio,
    "nivel": nivel,
    "area": area
  }

  http = Net::HTTP.new(uri.host, uri.port)
  request = Net::HTTP::Post.new(uri.request_uri, header)
  request.body = payload.to_json

  response = http.request(request)
  puts "#{response.code} #{valor}"
end

client = MQTT::Client.connect(:host => '172.24.42.32', :port => 8083 )
client.subscribe( 'registros' )

client.get do |topic,message|
  puts "-----------------------------------------"
  puts topic
  puts "-----------------------------------------"
  json = JSON.parse(message)
  puts json

  nivel = json['Nivel']
  area = json['Area']

  create_register(json['Temperature']['Value'], json['Temperature']['Promedio'], nivel, area)
  create_register(json['Sound']['Value'], json['Sound']['Promedio'], nivel, area)
  create_register(json['Monoxide']['Value'], json['Monoxide']['Promedio'], nivel, area)
  create_register(json['Lux']['Value'], json['Lux']['Promedio'], nivel, area)
  # TODO Mandar el place
end

client.disconnect()
