require 'rubygems'
require 'mqtt'
require 'json'

client = MQTT::Client.connect(:host => '172.24.42.32', :port => 8083 )
client.subscribe( 'alta.piso1.local1' )

client.get do |topic,message|
  puts "-----------------------------------------"
  puts topic
  puts "-----------------------------------------"
  json = JSON.parse(message)
  puts json
end

client.disconnect()
