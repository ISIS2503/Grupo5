class Api::ActuadorController < ApplicationController

  before_action :authenticate_user!

  def index
    render json: Api::Actuador.all, status: :ok
  end
end
