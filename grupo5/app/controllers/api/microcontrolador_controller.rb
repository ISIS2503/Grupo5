class Api::MicrocontroladorController < ApplicationController

	before_action :authenticate_user!

	def index
		render json: Api::Microcontrolador.all, status: :ok
	end
end
