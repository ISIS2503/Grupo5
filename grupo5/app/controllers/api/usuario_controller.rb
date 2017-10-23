class Api::UsuarioController < ApplicationController

  # GET /api/usuario
  # GET /api/usuario.json
	def index
		render json: Api::Usuario.all, status: :ok
	end
	
end


