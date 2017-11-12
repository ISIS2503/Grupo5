class Api::AlertController < ApplicationController

	before_action :authenticate_user!

  # GET /api/registro_mediciones
  # GET /api/registro_mediciones.json
		def index
    	render json: Api::Alert.all, status: :ok
  	end
end
