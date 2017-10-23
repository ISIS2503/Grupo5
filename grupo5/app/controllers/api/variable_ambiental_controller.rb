class Api::VariableAmbientalController < ApplicationController

def index

	render json: Api::VariableAmbiental.all, status: :ok
end

end
