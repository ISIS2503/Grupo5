require 'test_helper'

class RegistroMedicionesControllerTest < ActionDispatch::IntegrationTest
  setup do
    @registro_medicion = registro_mediciones(:one)
  end

  test "should get index" do
    get registro_mediciones_url
    assert_response :success
  end

  test "should get new" do
    get new_registro_medicion_url
    assert_response :success
  end

  test "should create registro_medicion" do
    assert_difference('RegistroMedicion.count') do
      post registro_mediciones_url, params: { registro_medicion: { tipo: @registro_medicion.tipo, unidad: @registro_medicion.unidad, valor: @registro_medicion.valor } }
    end

    assert_redirected_to registro_medicion_url(RegistroMedicion.last)
  end

  test "should show registro_medicion" do
    get registro_medicion_url(@registro_medicion)
    assert_response :success
  end

  test "should get edit" do
    get edit_registro_medicion_url(@registro_medicion)
    assert_response :success
  end

  test "should update registro_medicion" do
    patch registro_medicion_url(@registro_medicion), params: { registro_medicion: { tipo: @registro_medicion.tipo, unidad: @registro_medicion.unidad, valor: @registro_medicion.valor } }
    assert_redirected_to registro_medicion_url(@registro_medicion)
  end

  test "should destroy registro_medicion" do
    assert_difference('RegistroMedicion.count', -1) do
      delete registro_medicion_url(@registro_medicion)
    end

    assert_redirected_to registro_mediciones_url
  end
end
