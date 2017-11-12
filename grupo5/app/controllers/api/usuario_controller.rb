class Api::UsuarioController < ApplicationController

	before_action :authenticate_user!

  # GET /api/usuario
  # GET /api/usuario.json
	def index
		render json: Api::Usuario.all, status: :ok
	end

end
