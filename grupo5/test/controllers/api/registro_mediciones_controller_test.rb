require 'test_helper'

class Api::RegistroMedicionesControllerTest < ActionDispatch::IntegrationTest
  setup do
    @api_registro_medicion = api_registro_mediciones(:one)
  end

  test "should get index" do
    get api_registro_mediciones_url
    assert_response :success
  end

  test "should get new" do
    get new_api_registro_medicion_url
    assert_response :success
  end

  test "should create api_registro_medicion" do
    assert_difference('Api::RegistroMedicion.count') do
      post api_registro_mediciones_url, params: { api_registro_medicion: { tipo: @api_registro_medicion.tipo, unidad: @api_registro_medicion.unidad, valor: @api_registro_medicion.valor } }
    end

    assert_redirected_to api_registro_medicion_url(Api::RegistroMedicion.last)
  end

  test "should show api_registro_medicion" do
    get api_registro_medicion_url(@api_registro_medicion)
    assert_response :success
  end

  test "should get edit" do
    get edit_api_registro_medicion_url(@api_registro_medicion)
    assert_response :success
  end

  test "should update api_registro_medicion" do
    patch api_registro_medicion_url(@api_registro_medicion), params: { api_registro_medicion: { tipo: @api_registro_medicion.tipo, unidad: @api_registro_medicion.unidad, valor: @api_registro_medicion.valor } }
    assert_redirected_to api_registro_medicion_url(@api_registro_medicion)
  end

  test "should destroy api_registro_medicion" do
    assert_difference('Api::RegistroMedicion.count', -1) do
      delete api_registro_medicion_url(@api_registro_medicion)
    end

    assert_redirected_to api_registro_mediciones_url
  end
end
