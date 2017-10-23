class Api::AlertController < ApplicationController

  # GET /api/registro_mediciones
  # GET /api/registro_mediciones.json
		def index
    render json: Api::RegistroMedicion.all, status: :ok
  end
end