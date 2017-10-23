class Api::VariableAmbiental

  include Mongoid::Document

  field :tipo, type: String
  field :valorMaximo, type: Float
  field :valorMinimo, type: Float
  field :unidad, type: String
  field :variacionDiaria, type: Float
  field :precision, type: Float
  
end