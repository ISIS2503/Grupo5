json.extract! registro_medicion, :id, :valor, :tipo, :unidad, :created_at, :updated_at
json.url registro_medicion_url(registro_medicion, format: :json)
