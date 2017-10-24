class Api::RegistroMedicionesController < ApplicationController
  before_action :set_registro_medicion, only: [:show, :destroy]

  # GET /api/registro_mediciones
  # GET /api/registro_mediciones.json
  def index
    #Api::RegistroMedicionesHelper.delay(run_at: 60.minutes.from_now).send_email
    render json: Api::RegistroMedicion.all, status: :ok
  end

  # GET /api/registro_mediciones/1
  # GET /api/registro_mediciones/1.json
  def show
    render json: @registro_medicion.to_json, status: :ok
  end

  # POST /api/registro_mediciones
  # POST /api/registro_mediciones.json
  def create
    puts params.to_json

    @registro_medicion = Api::RegistroMedicion.new(registro_medicion_params)
    @registro_medicion.microcontrolador = Api::Microcontrolador.find_or_create_by(nivel: params[:nivel], area: params[:area])
    #TODO Mande que tipo de variable ambiental es
    @registro_medicion.variable_ambiental = Api::VariableAmbiental.last

    if Api::RegistroMedicionesHelper.verify(@registro_medicion, params[:promedio]) && @registro_medicion.save
      render json: @registro_medicion.to_json, status: :ok
    else
      render :json => { :mssg => 'Hubo un error creando el registro medicion.' }, status: :unprocessable_entity
    end
  end

  # DELETE /api/registro_mediciones/1
  # DELETE /api/registro_mediciones/1.json
  def destroy
    @registro_medicion.destroy
  end

  private
    def set_registro_medicion
      @registro_medicion = Api::RegistroMedicion.find(params[:id])
    end

    def registro_medicion_params
      params.require(:registro_medicion).permit(:valor, :tipo, :unidad)
    end
end
