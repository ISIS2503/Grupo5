class Api::AlertController < ApplicationController

	before_action :authenticate_user!
	before_action do |c|
		authorize_user!([Constants::SUPERVISOR, Constants::SERVICE , Constants::SYSO ])
	end

  # GET /api/registro_mediciones
  # GET /api/registro_mediciones.json
		def index
    	#render json: Api::Alert.all, status: :ok
  	end

		def alerts_area
			@alerts = Api::Alert.where(:microcontrolador_id.in => Api::Microcontrolador.where(area: params[:area]).pluck(:id))			
		end
end
