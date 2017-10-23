class Api::AlertController < ApplicationController

  # GET /api/registro_mediciones
  # GET /api/registro_mediciones.json
		def index
    	render json: Api::Alert.all, status: :ok
  	end
end
