class Api::MicrocontroladorController < ApplicationController

def index
	render json: Api::Microcontrolador.all, status: :ok
end



end
