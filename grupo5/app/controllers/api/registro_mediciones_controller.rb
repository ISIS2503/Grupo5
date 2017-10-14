class Api::RegistroMedicionesController < ApplicationController
  before_action :set_api_registro_medicion, only: [:show, :edit, :update, :destroy]

  # GET /api/registro_mediciones
  # GET /api/registro_mediciones.json
  def index
    @api_registro_mediciones = Api::RegistroMedicion.all
  end

  # GET /api/registro_mediciones/1
  # GET /api/registro_mediciones/1.json
  def show
  end

  # GET /api/registro_mediciones/new
  def new
    @api_registro_medicion = Api::RegistroMedicion.new
  end

  # GET /api/registro_mediciones/1/edit
  def edit
  end

  # POST /api/registro_mediciones
  # POST /api/registro_mediciones.json
  def create
    @api_registro_medicion = Api::RegistroMedicion.new(api_registro_medicion_params)

    respond_to do |format|
      if @api_registro_medicion.save
        format.html { redirect_to @api_registro_medicion, notice: 'Registro medicion was successfully created.' }
        format.json { render :show, status: :created, location: @api_registro_medicion }
      else
        format.html { render :new }
        format.json { render json: @api_registro_medicion.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /api/registro_mediciones/1
  # PATCH/PUT /api/registro_mediciones/1.json
  def update
    respond_to do |format|
      if @api_registro_medicion.update(api_registro_medicion_params)
        format.html { redirect_to @api_registro_medicion, notice: 'Registro medicion was successfully updated.' }
        format.json { render :show, status: :ok, location: @api_registro_medicion }
      else
        format.html { render :edit }
        format.json { render json: @api_registro_medicion.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /api/registro_mediciones/1
  # DELETE /api/registro_mediciones/1.json
  def destroy
    @api_registro_medicion.destroy
    respond_to do |format|
      format.html { redirect_to api_registro_mediciones_url, notice: 'Registro medicion was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_api_registro_medicion
      @api_registro_medicion = Api::RegistroMedicion.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def api_registro_medicion_params
      params.require(:api_registro_medicion).permit(:valor, :tipo, :unidad)
    end
end
