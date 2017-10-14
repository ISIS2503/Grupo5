json.extract! api_registro_medicion, :id, :valor, :tipo, :unidad, :created_at, :updated_at
json.url api_registro_medicion_url(api_registro_medicion, format: :json)
