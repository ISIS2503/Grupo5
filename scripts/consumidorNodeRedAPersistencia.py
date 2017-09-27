import json
import requests
from kafka import KafkaConsumer
 
consumer = KafkaConsumer('alta.piso1.local1',
                         group_id='temperature',
                         bootstrap_servers=['localhost:8090'])
 
for message in consumer:
	json_data = json.loads(message.value.decode('utf-8'))
	temperature = json_data['Temperature']
	sound = json_data['Sound']
	monoxide = json_data['Monoxide']
	lux = json_data['Lux']
	place = json_data['Place']
	
	url = 'http://localhost:9000/registros'
	
	payloadTemp = {
		'tipo': 'Temperature',
		'valor': temperature['Value'],
        'unidad': temperature['Unit']
    }
	response = requests.post(url, data=json.dumps(payloadTemp),headers={'Content-type': 'application/json'})
	
	payloadSound = {
		'tipo': 'Sound',
		'valor': sound['Value'],
        'unidad': sound['Unit']
    }
	response = requests.post(url, data=json.dumps(payloadSound),headers={'Content-type': 'application/json'})
	
	payloadMonoxide = {
		'tipo': 'Monoxide',
		'valor': monoxide['Value'],
        'unidad': monoxide['Unit']
    }
	response = requests.post(url, data=json.dumps(payloadMonoxide),headers={'Content-type': 'application/json'})
	
	payloadLux = {
		'tipo': 'Lux',
		'valor': lux['Value'],
        'unidad': lux['Unit']
    }
	response = requests.post(url, data=json.dumps(payloadLux),headers={'Content-type': 'application/json'})
	
	# TODO Para siguiente entrega es mandar el place
	
	print(message.topic)
	print("-------------------------------")