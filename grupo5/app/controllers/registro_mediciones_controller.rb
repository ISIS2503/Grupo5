class RegistroMedicionesController < ApplicationController
  before_action :set_registro_medicion, only: [:show, :edit, :update, :destroy]

  # GET /registro_mediciones
  # GET /registro_mediciones.json
  def index
    @registro_mediciones = RegistroMedicion.all
  end

  # GET /registro_mediciones/1
  # GET /registro_mediciones/1.json
  def show
  end

  # GET /registro_mediciones/new
  def new
    @registro_medicion = RegistroMedicion.new
  end

  # GET /registro_mediciones/1/edit
  def edit
  end

  # POST /registro_mediciones
  # POST /registro_mediciones.json
  def create
    @registro_medicion = RegistroMedicion.new(registro_medicion_params)

    respond_to do |format|
      if @registro_medicion.save
        format.html { redirect_to @registro_medicion, notice: 'Registro medicion was successfully created.' }
        format.json { render :show, status: :created, location: @registro_medicion }
      else
        format.html { render :new }
        format.json { render json: @registro_medicion.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /registro_mediciones/1
  # PATCH/PUT /registro_mediciones/1.json
  def update
    respond_to do |format|
      if @registro_medicion.update(registro_medicion_params)
        format.html { redirect_to @registro_medicion, notice: 'Registro medicion was successfully updated.' }
        format.json { render :show, status: :ok, location: @registro_medicion }
      else
        format.html { render :edit }
        format.json { render json: @registro_medicion.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /registro_mediciones/1
  # DELETE /registro_mediciones/1.json
  def destroy
    @registro_medicion.destroy
    respond_to do |format|
      format.html { redirect_to registro_mediciones_url, notice: 'Registro medicion was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_registro_medicion
      @registro_medicion = RegistroMedicion.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def registro_medicion_params
      params.require(:registro_medicion).permit(:valor, :tipo, :unidad)
    end
end
