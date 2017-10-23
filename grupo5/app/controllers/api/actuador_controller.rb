class Api::ActuadorController < ApplicationController

    def index
        render json: Api::Actuador.all, status: :ok
      end
end
